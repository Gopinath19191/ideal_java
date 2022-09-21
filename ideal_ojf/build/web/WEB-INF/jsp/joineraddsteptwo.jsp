<%@include file="header.jsp"  %>
<%@include file="joineraddcommonscripts.jsp" %>
<c:if test="${printStatus!='yes'}">

</c:if>
<title>Employee Details - Step 2 of 4</title>
<script type="text/javascript" src="javascript/steptwo.js"></script>
<script type="text/javascript">
 
    
    $(document).ready(function() {
               
       $(".datePickerYear").each(function() {
            $(this).rules("add", {
                min: 1910,
                max: 2100,
                minlength: 4,
                digits: true
            });
        });
        $(".dateOfRel").datepicker({
            changeYear: true,
            changeMonth: true,
            dateFormat: 'dd-mm-yy',
            showButtonPanel: true,
            yearRange: '1900:' + (new Date().getFullYear() + 30)
//            maxDate: "0"
        });

        $(".dateOfJoin").datepicker({
            changeYear: true,
            changeMonth: true,
            dateFormat: 'dd-mm-yy',
            showButtonPanel: true,
            yearRange: '1900:' + new Date().getFullYear()
//            minDate: "-60Y", maxDate: "0",
//            onSelect: function(dateText, inst) {
//                $(this).parent().parent().find(".dateOfRel").datepicker("option" ,"minDate", new Date(dateText.split("-")[2], --dateText.split("-")[1] ,dateText.split("-")[0]));
//            }
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

    function checkEmptyValue(countId, mandatoryValueId, tableName, empId, errorMessage, deleteStatusId) {
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

    function yearValidationX2X(X2Value, errorId, Xvalue) {
        if (Xvalue != "" && X2Value != "") {
            if (X2Value < Xvalue || X2Value < parseInt(Xvalue) + parseInt(2)) {
                alert("Invalid Year");
            }
        }
    }
    function checkMaxLength(id, selectedValue) {
        if (selectedValue.length > 25) {
            alert("Only 25 Characters allowed for each text box.If you want more click Add Technical Skill.")
            document.getElementById(id).value = selectedValue.substring(0, 25);
        }
    }
   


</script>
<body class="ext-gecko ext-gecko3" >
    <div id="container">
        <%@include file="menu.jsp" %>
        <div class="LabelHeader">Employee Details - Step 2 of 4</div>
        <div class="notice_page">
            <div style="float:left;"><img alt="Information" title="Information" src="css/images/icon_alert.png" /></div>
            <div style="padding-left:20px;">
                <ul class="notice_page_ul">
                    <li>Fields marked in <font color="red" size="3">*</font> are mandatory</li>
                    <li>Previous Employer, Technical skills & Educational Qualifications sections are mandatory</li>
                    <li>Educational Qualifications - X Std & Either +2 / Diploma are mandatory</li>
                    <li>Please validate the data before submit & It will be subjected to verify</li>
                </ul>
            </div>
        </div>
        <input type="hidden" value="${pageContext.request.contextPath}" id="base_path" />
        <div style="display:none">
            <select id="streamFirst" name="streamFirst" style="width:100%">
                <option value="" >--Select Stream--</option>
                <c:forEach items="${stream}" var="item">
                    <option ${selval} value="${item.streamId}" >${item.streamName}</option>
                </c:forEach>
            </select>
            <select id="qualificationFirst" name="qualificationFirst" style="width:100%">
                <option value="">-- Select Qualification --</option>
                <c:forEach items="${qualification}" var="item">
                    <option ${selval} value="${item.courseId}" >${item.courseName}</option>
                </c:forEach>
            </select>
            <select id="institutionFirst" name="institutionFirst" style="width:100%">
                <option value="">-- Select Institution --</option>
                <c:forEach items="${institution}" var="item">
                    <option ${selval} value="${item.instituteId}" >${item.instituteName}</option>
                </c:forEach>
            </select>
            <select id="universityFirst" name="universityFirst" style="width:100%">
                <option value="">-- Select University --</option>
                <c:forEach items="${university}" var="item">
                    <option ${selval} value="${item.universityId}" >${item.universityName}</option>
                </c:forEach>
            </select>
        </div>
        <c:choose>
            <c:when test="${groupId!=joinerGroupId}">
                <form action="joinerFormAddOrUpdateStepThree.htm" method="POST" name="joinerFormOne" id="idealJavaForm" class="idealJavaForm" enctype="multipart/form-data" onSubmit="return form_validate();">
                </c:when>
                <c:otherwise>
                    <form action="joinerFormAddOrUpdateStepThree.htm" method="POST" name="joinerFormOne" id="idealJavaForm" class="idealJavaForm" enctype="multipart/form-data" onSubmit="return form_validate();">
                    </c:otherwise>
                </c:choose>
                <input type="hidden" name="jfId" value ="${employeeData.jfId}" id="jfId"/>
                <div id="commonforms" >
                    <div class="commonformheader">Previous Employer Details</div>
                    <table width="100%">
                        <tr>
                            <td colspan="3">
                                <c:choose>
                                    <c:when test="${!empty(prevEmpDetail)}">                               
                                        <input type="hidden" name="employer_recordCount" id="employer_recordCount" value="${fn:length(prevEmpDetail)}" />
                                    </c:when>
                                    <c:otherwise>               
                                        <input type="hidden" name="employer_recordCount" id="employer_recordCount" value="1" />
                                    </c:otherwise>
                                </c:choose>
                                <table width="100%" border="0" cellspacing="5">
                                    <tr class="headerCenter">
                                        <th width="10%" valign="top">Company Name<font color="red" size="3">*</font></th>
                                        <th width="10%" valign="top">Address<font color="red" size="3">*</font></th>
                                        <th width="9%" valign="top">Date of Joining<font color="red" size="3">*</font></th>
                                        <th width="7%" valign="top">Designation on Joining<font color="red" size="3">*</font></th>
                                        <th width="8%" valign="top">Salary on &nbsp;&nbsp; Joining (P.A)<font color="red" size="3">*</font></th>
                                        <th width="9%" valign="top">Date of Relieving</th>
                                        <th width="7%" valign="top">Designation on Relieving<font color="red" size="3">*</font></th>
                                        <th width="8%" valign="top">Salary on Relieving<font color="red" size="3">*</font></th>
                                        <th width="10%" valign="top">Experience<font color="red" size="3">*</font></th>
                                        <th width="10%" valign="top">Attachment<font color="red" size="3">*</font></th>
                                        <th width="4%">&nbsp;</th>    
                                    </tr>

                                    <c:forEach items="${prevEmpDetail}" var="prevEmpDetail" varStatus="previndex">
                                        <tr id="EMP_TR_${previndex.count}">
                                        <input type="hidden" name="jfPreEmpId" id="jfPreEmpId_${previndex.count}" value="${prevEmpDetail.jfPreEmpIdDb}" />
                                        <input type="hidden" name="EMP_TR_deleted" id="EMP_TR_deleted_${previndex.count}" value="0" />
                                        <td valign="top">
                                            <input type="text" style="width:100%" name="namePrevEmp" id="namePrevEmp_${previndex.count}"  value="${prevEmpDetail.namePrevEmpDb}" />
                                            <span style="color:red;display:none;" id="namePrevEmp_error_${previndex.count}">*required</span>
                                            <div id = "prevEmpName_${previndex.count}" style="display:none;"><font color='red'>Only Alphabets are allowed</font></div>
                                        </td>
                                        <td valign="top">
                                            <input type="text" style="width:100%" name="nameAddPrevEmp" id="nameAddPrevEmp_${previndex.count}" value="${prevEmpDetail.nameAddPrevEmpDb}" />
                                            <span style="color:red;display:none;" id="nameAddPrevEmp_error_${previndex.count}">*required</span>
                                            <div id = "prevEmpAdd_${previndex.count}" style="display:none;"><font color='red'>Only Alpha Numerics are allowed</font></div>
                                        </td>
                                        <td valign="top">
                                            <input type="text" style="width:100%" class="dateOfJoin" name="dateOfJoin" id="dateOfJoin_${previndex.count}"  value="${prevEmpDetail.dateOfJoinDb}" readonly />
                                            <span style="color:red;display:none;" id="dateOfJoin_error_${previndex.count}">*required</span>
                                        </td>
                                        <td valign="top">
                                            <input type="text" style="width:100%" name="desigOnJoin" id="desigOnJoin_${previndex.count}" value="${prevEmpDetail.desigOnJoinDb}"/>
                                            <span style="color:red;display:none;" id="desigOnJoin_error_${previndex.count}">*required</span>
                                            <div id = "prevEmpDesigOnJoin_${previndex.count}" style="display:none;"><font color='red'>Only Alpha Numerics are allowed</font></div>
                                        </td>
                                        <td valign="top">
                                            <input type="text" style="width:100%" name="salaryOnJoin" id="salaryOnJoin_${previndex.count}" value="${prevEmpDetail.salaryOnJoinDb}" onkeypress="return blockNonNumbers(this, event, false, false);" onkeyup="return extractNumber(this, 2, false);" maxlength="9" />
                                            <span style="color:red;display:none;" id="salOnJoin_error_${previndex.count}">*required</span>
                                        </td>
                                        <td valign="top">
                                            <input type="text" style="width:100%" class="dateOfRel" name="dateOfRel" id="dateOfRel_${previndex.count}"  value="${prevEmpDetail.dateOfRelDb}" readonly />
                                            <span style="color:red;display:none;" id="dateOfRel_error_${previndex.count}">*required</span>
                                        </td>
                                        <td valign="top">
                                            <input type="text" style="width:100%" name="desigOnRel" id="desigOnRel_${previndex.count}" value="${prevEmpDetail.desigOnRelDb}" />
                                            <span style="color:red;display:none;" id="desigOnRel_error_${previndex.count}">*required</span>
                                            <div id = "prevEmpdesigOnRel_${previndex.count}" style="display:none;"><font color='red'>Only Alpha Numerics are allowed</font></div>
                                        </td>
                                        <td valign="top">
                                            <input type="text" style="width:100%" name="salOnRel" id="salOnRel_${previndex.count}" value="${prevEmpDetail.salOnRelDb}" onkeypress="return blockNonNumbers(this, event, false, false);" onkeyup="return extractNumber(this, 2, false);" maxlength="9" />
                                            <span style="color:red;display:none;" id="salOnRel_error_${previndex.count}">*required</span> 
                                        </td>
                                        <td valign ="top">
                                            <select id="exp_year_${previndex.count}" name="exp_year" style="width:48%;">
                                                <option value="-1" >--Year--</option>
                                                <c:forEach  var="extotyear" varStatus="i"  begin="0" end="20">
                                                    <c:set var="selval" value="" ></c:set>
                                                    <c:if test="${extotyear == prevEmpDetail.expYear}">
                                                        <c:set var="selval" value="selected=selected" ></c:set>
                                                    </c:if>
                                                    <option ${selval} value="${extotyear}">${extotyear}</option>
                                                </c:forEach>
                                            </select>
                                            <select id="exp_month_${previndex.count}" name="exp_month" style="width:48%">
                                                <option value="-1" >--Month--</option>
                                                <c:forEach  var="extotyear" varStatus="i"  begin="0" end="20">
                                                    <c:set var="selval" value="" ></c:set>
                                                    <c:if test="${extotyear == prevEmpDetail.expMonth}">
                                                        <c:set var="selval" value="selected=selected" ></c:set>
                                                    </c:if>
                                                    <option ${selval} value="${extotyear}">${extotyear}</option>
                                                </c:forEach>
                                            </select>
                                            <span style="color:red;display:none;" id="exp_year_error_${previndex.count}">*required</span>
                                            <span style="color:red;display:none;" id="exp_month_error_${previndex.count}">*required</span>
                                        </td>
                                        <td valign="top">
                                            <input type="file" size="8" id="exp_attachment_${previndex.count}" name="exp_attachment_${previndex.count}" style="width: 120px;" /><br/>
                                            <c:choose>
                                                <c:when test="${prevEmpDetail.exp_attachmentX != '' && prevEmpDetail.exp_attachmentX != 'NULL'}">
                                                    <input type="hidden" name="exp_attachmentXY" value="${prevEmpDetail.exp_attachmentX}" id="exp_attachmentXY_${previndex.count}" >
                                                    <c:set var="DOCS" value="${fn:split(prevEmpDetail.exp_attachmentX, '~~')}" />
                                                    <a href="fileDownload.htm?fileName=${prevEmpDetail.exp_attachmentX}"><c:out value="${DOCS[2]}"></c:out></a>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="hidden" name="exp_attachmentXY" id="exp_attachmentXY_${previndex.count}" value="" >
                                                </c:otherwise>
                                            </c:choose>
                                            <span style="color:red;display:none;" id="exp_attachment_error_${previndex.count}">*required</span>
                                            <div id="exp_attachment_err_${previndex.count}" style="color:red;display: none;">.zip, .jar, .war & .rar files are not accepted</div>
                                        </td>
                                        <td valign="top" align="center">
                                            <img src="${pageContext.request.contextPath}/css/images/delete-icon.png" onclick="deleteRow('EMP_TR',${previndex.count}, 1)" />
                                        </td>
                            </tr>
                        </c:forEach>



                        <c:if test="${empty(prevEmpDetail)}">
                            <tr id="EMP_TR_1">
                            <input type="hidden" name="jfPreEmpId" id="jfPreEmpId_1" value="" />
                            <input type="hidden" name="EMP_TR_deleted" id="EMP_TR_deleted_1" value="0" />
                            <td valign ="top">
                                <input type="text" style="width:100%" name="namePrevEmp" id="namePrevEmp_1"/>
                                <span style="color:red;display:none;" id="namePrevEmp_error_1">*required</span>
                                <div id = "prevEmpName_1" style="display:none;"><font color='red'>Only Alphabets are allowed</font></div>
                            </td>
                            <td valign ="top">
                                <input type="text" style="width:100%" name="nameAddPrevEmp" id="nameAddPrevEmp_1" />
                                <span style="color:red;display:none;" id="nameAddPrevEmp_error_1">*required</span>
                                <div id = "prevEmpAdd_1" style="display:none;"><font color='red'>Only Alpha Numerics are allowed</font></div>
                            </td>
                            <td valign ="top">
                                <input type="text" style="width:100%" class="dateOfJoin" name="dateOfJoin" id="dateOfJoin_1" readonly />
                                <span style="color:red;display:none;" id="dateOfJoin_error_1">*required</span>
                            </td>
                            <td valign ="top">
                                <input type="text" style="width:100%" name="desigOnJoin" id="desigOnJoin_1"  />
                                <span style="color:red;display:none;" id="desigOnJoin_error_1">*required</span>
                                <div id = "prevEmpDesigOnJoin_1" style="display:none;"><font color='red'>Only Alpha Numerics are allowed</font></div>
                            </td>
                            <td valign ="top">
                                <input type="text" style="width:100%" name="salaryOnJoin" id="salaryOnJoin_1"  onkeypress="return blockNonNumbers(this, event, false, false);" onkeyup="return extractNumber(this, 2, false);" maxlength="9" />
                                <span style="color:red;display:none;" id="salOnJoin_error_1">*required</span>
                            </td>
                            <td valign ="top">
                                <input type="text" style="width:100%" class="dateOfRel" name="dateOfRel" id="dateOfRel_1" readonly  />
                                <span style="color:red;display:none;" id="dateOfRel_error_1">*required</span>
                            </td>
                            <td valign ="top">
                                <input type="text" style="width:100%" name="desigOnRel" id="desigOnRel_1" />
                                <span style="color:red;display:none;" id="desigOnRel_error_1">*required</span>
                                <div id = "prevEmpDesigOnRel_1" style="display:none;"><font color='red'>Only Alpha Numerics are allowed</font></div>
                            </td>
                            <td valign ="top">
                                <input type="text" style="width:100%" name="salOnRel" id="salOnRel_1" onkeypress="return blockNonNumbers(this, event, false, false);" onkeyup="return extractNumber(this, 2, false);" maxlength="9" />
                                <span style="color:red;display:none;" id="salOnRel_error_1">*required</span>
                            </td>
                            <td valign ="top">
                                <select id="exp_year_1" name="exp_year" style="width:48%">
                                    <option value="-1" >--Year--</option>
                                    <c:forEach  var="extotyear" varStatus="i"  begin="0" end="20">
                                        <option  value="${extotyear}">${extotyear}</option>
                                    </c:forEach>
                                </select>                                
                                <select id="exp_month_1" name="exp_month" style="width:48%">
                                    <option value="-1" >--Month--</option>
                                    <c:forEach  var="extotmonth" varStatus="i"  begin="0" end="11">
                                        <option  value="${extotmonth}">${extotmonth}</option>
                                    </c:forEach>
                                </select>
                                <span style="color:red;display:none;" id="exp_year_error_1">*required</span>
                                <span style="color:red;display:none;" id="exp_month_error_1">*required</span>
                            </td>
                            <td valign ="top">
                                <input type="hidden" name="exp_attachmentXY" id="exp_attachmentXY_1" value="" />
                                <input type="file" size="8" id=exp_attachment_1" name="exp_attachment_1" style="width: 120px;"><br>
                                <span style="color:red;display:none;" id="exp_attachment_error_1">*required</span>
                                <div id="exp_attachment_err_1" style="color:red;display: none;">.zip, .jar, .war & .rar files are not accepted</div>
                            </td>
                            </tr>
                        </c:if>




                    </table>
                    <table cellspacing="5">
                        <tr>
                            <td><input type="button" class="addmore" onclick="addRow_Employer();" value="Add"  /></td>
                        </tr>
                    </table>
                    </td>
                    </tr>
                    </table>
                </div>
                <div id="commonforms">
                    <div class="commonformheader">Technical skills</div>
                    <table width="100%">
                        <tr>
                            <td colspan="3">
                                <c:choose>
                                    <c:when test="${!empty(skillDetails)}">                               
                                        <input type="hidden" name="skill_recordCount" id="skill_recordCount" value="${fn:length(skillDetails)}" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="skill_recordCount" id="skill_recordCount" value="1" />
                                    </c:otherwise>
                                </c:choose>
                                <table width="100%" border="0" cellspacing="5">
                                    <tr class="headerCenter">
                                        <th width="15%">Category<font color="red" size="3">*</font></th>
                                        <th width="22%">Stream<font color="red" size="3">*</font></th>
                                        <th width="22%">Skill<font color="red" size="3">*</font></th>
                                        <th width="15%">Skill Type<font color="red" size="3">*</font></th>
                                        <th width="22%">Experience<font color="red" size="3">*</font></th>
                                        <th width="4%">&nbsp;</th>
                                    </tr>

                                    <c:forEach items="${skillDetails}" var="SkillDetails" varStatus="SkillStatus">
                                        <tr id="SK_TR_${SkillStatus.count}">
                                        <input type="hidden" name="skillId" id="skillId_${SkillStatus.count}" value="${SkillDetails.skillIdX}" />
                                        <input type="hidden" name="SK_TR_deleted" id="SK_TR_deleted_${SkillStatus.count}" value="0" />
                                        <td valign ="top">
                                            <select id="skill_category_${SkillStatus.count}" name="skill_category" style="width:100%;" onchange="change(this.value, 1)">
                                                <option value="" >--Select Category--</option>
                                                <option ${(SkillDetails.skill_categoryX == 'T' )?'selected':''} value="T">Technical</option>
                                                <option ${(SkillDetails.skill_categoryX == 'N' )?'selected':''} value="N">Non-Technical</option>
                                                <option ${(SkillDetails.skill_categoryX == 'F' )?'selected':''} value="F">Functional</option>
                                            </select>
                                            <span style="color:red;display:none;" id="skill_category_error_${SkillStatus.count}">*required</span>
                                        </td>
                                        <td valign ="top">
                                            <input type="hidden" value="${SkillDetails.streamX}" id="streamSelected_${SkillStatus.count}" />
                                            <select id="stream_${SkillStatus.count}" name="stream" onChange="changeSkill(this.value,${SkillStatus.count})" style="width:100%">
                                                <option value="" >--Select Stream--</option>
                                            </select>
                                            <span style="color:red;display:none;" id="stream_error_${SkillStatus.count}">*required</span>
                                        </td>
                                        <td valign ="top">
                                            <input type="hidden" value="${SkillDetails.skillX}" id="selected_skill_${SkillStatus.count}" />
                                            <select id="skill_${SkillStatus.count}" name="skill" style="width:100%">
                                                <option value="" >--Select Skill--</option>
                                                <c:forEach items="${skill}" var="item">
                                                    <option value="${item.skillId}" >${item.skillName}</option>
                                                </c:forEach>
                                            </select>
                                            <span style="color:red;display:none;" id="skill_error_${SkillStatus.count}">*required</span>
                                        </td>
                                        <td valign ="top">
                                            <select id="skill_type_${SkillStatus.count}" name="skill_type" style="width:100%">
                                                <option value="" >--Select Skill Type--</option>
                                                <option ${(SkillDetails.skill_typeX == 'P' )?'selected':''} value="P">Primary</option>
                                                <option ${(SkillDetails.skill_typeX == 'S' )?'selected':''} value="S">Secondary</option>
                                            </select>
                                            <span style="color:red;display:none;" id="skill_type_error_${SkillStatus.count}">*required</span>
                                        </td>
                                        <td valign ="top">
                                            <select id="skill_year_${SkillStatus.count}" name="skill_year" style="width:40%">
                                                <option value="" >--Year--</option>
                                                <c:forEach  var="extotyear" varStatus="i"  begin="0" end="20">
                                                    <c:set var="selval" value="" ></c:set>
                                                    <c:if test="${extotyear == SkillDetails.skill_yearX}">
                                                        <c:set var="selval" value="selected=selected" ></c:set>
                                                    </c:if>
                                                    <option ${selval} value="${extotyear}">${extotyear}</option>
                                                </c:forEach>
                                            </select>&nbsp;
                                            <select id="skill_month_${SkillStatus.count}" name="skill_month" style="width:40%">
                                                <option value="" >--Month--</option>
                                                <c:forEach  var="extotmonth" varStatus="i"  begin="0" end="11">
                                                    <c:set var="selval" value="" ></c:set>
                                                    <c:if test="${extotmonth == SkillDetails.skill_monthX}">
                                                        <c:set var="selval" value="selected=selected" ></c:set>
                                                    </c:if>
                                                    <option ${selval} value="${extotmonth}">${extotmonth}</option>
                                                </c:forEach>
                                            </select>
                                            <span style="color:red;display:none" id="skill_month_error_${SkillStatus.count}">*required</span>
                                        </td>
                                        <td valign="top" align="center">
                                            <c:if test="${SkillStatus.count != 1}">
                                                <img src="css/images/delete-icon.png" onclick="deleteRow('SK_TR',${SkillStatus.count}, 1)" />
                                            </c:if>
                                        </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty(skillDetails)}">
                            <tr id="SK_TR_1">
                            <input type="hidden" name="skillId" id="skillId_1" value="0" />
                            <input type="hidden" name="SK_TR_deleted[]" id="SK_TR_deleted_1" value="0" />
                            <td valign ="top">
                                <select id="skill_category_1" name="skill_category" style="width:100%;" >
                                    <option value="" >--Select Category--</option>
                                    <option value="T">Technical</option>
                                    <option value="N">Non-Technical</option>
                                    <option value="F">Functional</option>
                                </select>
                                <span style="color:red;display:none;" id="skill_category_error_1">*required</span>
                            </td>
                            <td valign ="top">
                                <select id="stream_1" name="stream" onChange="changeSkill(this.value, 1)" style="width:100%">
                                    <option value="" >--Select Stream--</option>
                                </select>
                                <span style="color:red;display:none;" id="stream_error_1">*required</span>
                            </td>
                            <td valign ="top">
                                <input type="hidden" value="" id="selected_skill_1" />
                                <select id="skill_1" name="skill" style="width:100%">
                                    <option value="" >--Select Skill--</option>
                                    <c:forEach items="${skill}" var="item">
                                        <option value="${item.skillId}" >${item.skillName}</option>
                                    </c:forEach>
                                </select>
                                <span style="color:red;display:none;" id="skill_error_1">*required</span>
                            </td>
                            <td valign ="top">
                                <select id="skill_type_1" name="skill_type" style="width:100%">
                                    <option value="" >--Select Skill Type--</option>
                                    <option value="P">Primary</option>
                                    <option value="S">Secondary</option>
                                </select>
                                <span style="color:red;display:none;" id="skill_type_error_1">*required</span>
                            </td>
                            <td valign ="top">
                                <select id="skill_year_1" name="skill_year" style="width:45%">
                                    <option value="" >--Year--</option>
                                    <c:forEach  var="extotyear" varStatus="i"  begin="0" end="20">
                                        <option  value="${extotyear}">${extotyear}</option>
                                    </c:forEach>
                                </select>
                                <select id="skill_month_1" name="skill_month" style="width:45%">
                                    <option value="" >--Month--</option>
                                    <c:forEach  var="extotmonth" varStatus="i"  begin="0" end="11">
                                        <option  value="${extotmonth}">${extotmonth}</option>
                                    </c:forEach>
                                </select>
                                <span style="color:red;display:none;" id="skill_month_error_1">*required</span>
                            </td>
                            </tr>
                        </c:if>
                    </table>
                    <table>
                        <tr>
                            <td><input type="button" class="addmore" onclick="addRow_Skill();" value="Add"  /></td>
                        </tr>
                    </table>
                    </td>
                    </tr>
                    </table>
                </div>
                <div id="commonforms">
                    <div class="commonformheader">Educational Qualifications</div>
                    <table width="100%" border="0">
                        <tr>
                            <td colspan="3">
                                <c:choose>
                                    <c:when test="${!empty(educationDetails)}">
                                        <input type="hidden" name="education_recordCount" id="education_recordCount" value="${fn:length(educationDetails)}" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="education_recordCount" id="education_recordCount" value="1" />
                                    </c:otherwise>
                                </c:choose>
                                <input type="hidden" id="eduDegree" value="" />
                                <table width="100%" border="0" cellspacing="5">
                                    <tr class="headerCenter">
                                        <th width="10%">Degree<font color="red" size="3">*</font></th>
                                        <th width="12%">Qualification<font color="red" size="3">*</font></th>
                                        <th width="6%">Year of passing<font color="red" size="3">*</font></th>
                                        <th width="14%">Institution<font color="red" size="3">*</font></th>
                                        <th width="12%">University<font color="red" size="3">*</font></th>
                                        <th width="6%">Percentage/ CGPA<font color="red" size="3">*</font></th>
                                        <th width="16%">Remarks</th>
                                        <th width="20%">Proof Attachment<font color="red" size="3">*</font></th>
                                        <th width="4%">&nbsp;</th>
                                    </tr>
                                    <c:forEach items="${educationDetails}" var="EducationDetails" varStatus="EducationStatus">
                                        <tr id="EDU_TR_${EducationStatus.count}">
                                        <input type="hidden" name="educationId" id="educationId_${EducationStatus.count}" value="${EducationDetails.educationIdX}" />
                                        <input type="hidden" name="EDU_TR_deleted" id="EDU_TR_deleted_${EducationStatus.count}" value="0" />
                                        <td valign ="top">
                                            <select id="degree_${EducationStatus.count}" name="degree" onchange="change(this.value,${EducationStatus.count});" style="width:100%">
                                                <option value="" >--Select Degree--</option>
                                                <c:forEach items="${degree}" var="item">
                                                    <c:set var="selval" value="" ></c:set>
                                                    <c:if test="${item.degreeId == EducationDetails.degreeX}">
                                                        <c:set var="selval" value="selected='selected'" ></c:set>
                                                    </c:if>
                                                    <option ${selval} value="${item.degreeId}" >${item.degreeName}</option>
                                                </c:forEach>
                                            </select>
                                            <span style="color:red;display:none;" id="degree_error_${EducationStatus.count}">*required</span>
                                        </td>
                                        <td valign ="top">
                                            <span id="span1_qualification_${EducationStatus.count}" style="display:${(EducationDetails.degreeX == 10 || EducationDetails.degreeX == 12 )?'none':'block'}" >
                                                <c:choose>
                                                    <c:when test="${EducationDetails.qualificationName == '0'}">
                                                        <input type="hidden" value="${EducationDetails.qualificationX}" id="qualificationSelected_${EducationStatus.count}" />
                                                        <select id="qualification_${EducationStatus.count}" name="qualification" style="width:100%">
                                                            <option value="" >--Select Qualification--</option>
                                                        </select>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="text" name="qualification" id="qualification_${EducationStatus.count}" value="${EducationDetails.qualificationX}" style="width:100%" />
                                                    </c:otherwise>
                                                </c:choose>

                                            </span>
                                            <span style="display:${(EducationDetails.degreeX == 10 || EducationDetails.degreeX == 12 )?'block':'none'}" id="span2_qualification_${EducationStatus.count}" ><input type="text" name="qualification1" id="qualification1_${EducationStatus.count}" value="${EducationDetails.qualificationX}" style="width:100%"/></span>
                                            <span style="color:red;display:none;" id="qualification_error_${EducationStatus.count}">*required</span>
                                            <div id = "qualificationErr_${EducationStatus.count}" style="display:none;"><font style = "color: red;">Only Alpha Numerics are allowed</font></div>
                                        </td>
                                        <td valign ="top">
                                            <input type="text" id="year_of_passing_${EducationStatus.count}" name="year_of_passing" value="${EducationDetails.year_of_passingX}" style="width:100%" onkeypress="return blockNonNumbers(this, event, false, false);" onkeyup="return extractNumber(this, 2, false);" maxlength="4" />
                                            <span style="color:red;display:none;" id="year_of_passing_error_${EducationStatus.count}">*required</span>
                                            <div id = "yearOfPassing_err_${EducationStatus.count}" style="display:none;"><font style = "color: red;">Enter valid Year</font></div>
                                        </td>
                                        <td valign ="top">
                                            <span id="span1_institution_${EducationStatus.count}" style="display:${(EducationDetails.degreeX == 10 || EducationDetails.degreeX == 12 )?'none':'block'}" >
                                                <c:choose>
                                                    <c:when test="${EducationDetails.instituteName == '0'}">
                                                        <input type="hidden" value="${EducationDetails.institutionX}" id="institutionSelected_${EducationStatus.count}" />
                                                        <select id="institution_${EducationStatus.count}" name="institution" style="width:100%" onchange="loadTextBox(this.value,${EducationStatus.count});" >
                                                            <option value="" >--Select Institution--</option>
                                                        </select>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="text" name="institution" id="institution_${EducationStatus.count}" value="${EducationDetails.institutionX}" style="width:100%" />
                                                    </c:otherwise>
                                                </c:choose>

                                            </span>
                                                
                                            <div id = "other_institution_${EducationStatus.count}" style="display:${(EducationDetails.degreeX == 10 || EducationDetails.degreeX == 12 || EducationDetails.institutionX != 1205) ?'none':'block'}" > 
                                                <label>Institution Name</label>
                                                <input type="text" id="other_institution_tb${EducationStatus.count}" value="${EducationDetails.otherInstitute}" name="otherInstitute" style="width: 100%">
                                                <span style="color:red;display:none;" id="other_institution_error_${EducationStatus.count}" >*required</span>
                                                </div>
                                                  
                                            <span style="display:${(EducationDetails.degreeX == 10 || EducationDetails.degreeX == 12 )?'block':'none'}" id="span2_institution_${EducationStatus.count}" ><input type="text" name="institution1" id="institution1_${EducationStatus.count}" value="${EducationDetails.institutionX}" style="width:100%" /></span>
                                            <span style="color:red;display:none;" id="institution_error_${EducationStatus.count}">*required</span>
                                            <div id = "institution_err_${EducationStatus.count}" style = "display: none;"><font style = "color:red;">Only Alphanumerics are allowed</font></div>
                                        </td>
                                        <td valign ="top">
                                            <span id="span1_university_${EducationStatus.count}" style="display:${(EducationDetails.degreeX == 10 || EducationDetails.degreeX == 12 )?'none':'block'}" >
                                                <c:choose>
                                                    <c:when test="${EducationDetails.universityName == '0'}">
                                                        <input type="hidden" value="${EducationDetails.universityX}" id="universitySelected_${EducationStatus.count}" />
                                                        <select id="university_${EducationStatus.count}" name="university" style="width:100%" onchange="loadUniversityText(this.value,${EducationStatus.count});">
                                                            <option value="" >--Select University--</option>
                                                        </select>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="text" name="university" id="university_${EducationStatus.count}" value="${EducationDetails.universityX}" style="width:100%"/>
                                                    </c:otherwise>
                                                </c:choose>

                                           </span>
                                                
                                            <div id = "other_university_${EducationStatus.count}" style="display:${(EducationDetails.degreeX == 10 || EducationDetails.degreeX == 12 || EducationDetails.universityX != 605) ?'none':'block'}" >  
                                                <label>University Name</label>
                                                <input type="text" id="other_university_tb${EducationStatus.count}" value="${EducationDetails.otheruniversity}" name="otheruniversity"  style="width: 100%">
                                                <span style="color:red;display:none;" id="other_university_error_${EducationStatus.count}">*required</span>
                                                </div> 
                                                
                                            <span style="display:${(EducationDetails.degreeX == 10 || EducationDetails.degreeX == 12 )?'block':'none'}" id="span2_university_${EducationStatus.count}" ><input type="text" name="university1" id="university1_${EducationStatus.count}" value="${EducationDetails.universityX}" style="width:100%"/></span>
                                            <span style="color:red;display:none;" id="university_error_${EducationStatus.count}">*required</span>
                                            <div id = "university_err_${EducationStatus.count}" style = "display: none;"><font style = "color:red;">Only Alphanumerics are allowed</font></div>
                                        </td>
                                        <td valign ="top">
                                            <input type="text" maxlength="5" id="percentage_${EducationStatus.count}" name="percentage" value="${EducationDetails.percentageX}" style="width:100%" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this, 2, false);" />
                                            <span style="color:red;display:none;" id="percentage_error_${EducationStatus.count}">*required</span>
                                        </td>
                                        <td valign ="top">
                                            <input type="text" maxlength="50" id="remarks_${EducationStatus.count}" name="remarks" value="${EducationDetails.remarksX}"  style="width:100%"/>
                                        </td>
                                        <td valign ="top">
                                            <input type="file" size="8" id="attachment_${EducationStatus.count}" name="attachment_${EducationStatus.count}" /><br />
                                            <c:choose>
                                                <c:when test="${EducationDetails.attachmentX != '' && EducationDetails.attachmentX != 'NULL'}">
                                                    <input type="hidden" name="attachmentXY" value="${EducationDetails.attachmentX}" id="attachmentXY_${EducationStatus.count}" >
                                                    <c:set var="DOCS" value="${fn:split(EducationDetails.attachmentX, '~~')}" />
                                                    <a href="fileDownload.htm?fileName=${EducationDetails.attachmentX}"><c:out value="${DOCS[2]}"></c:out></a>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="hidden" name="attachmentXY" id="attachmentXY_${EducationStatus.count}" value="" >
                                                </c:otherwise>
                                            </c:choose>
                                            <span style="color:red;display:none;" id="attachment_error_${EducationStatus.count}">*required</span>
                                            <div id="attachment_err_${EducationStatus.count}" style="color:red;display: none;">.zip, .jar, .war & .rar files are not accepted</div>
                                        </td>
                                        <td valign="top">
                                            <c:if test="${EducationDetails.degreeX != 10 && EducationDetails.degreeX != 12}">
                                                <img src="${pageContext.request.contextPath}/css/images/delete-icon.png" onclick="deleteRow('EDU_TR',${EducationStatus.count}, 1)" />
                                            </c:if>
                                        </td>

                            </tr> 
                        </c:forEach>
                            


                        <c:if test="${empty(educationDetails)}">
                            <tr id="EDU_TR_1">
                            <input type="hidden" name="educationId" id="educationId_1" value="" />
                            <input type="hidden" name="EDU_TR_deleted" id="EDU_TR_deleted_1" value="0" />
                            <td valign ="top">
                                <select id="degree_1" name="degree" style="width:100%;" onchange="change(this.value, 1)">
                                    <option value="">--Select Degree--</option>
                                    <c:forEach items="${degree}" var="item">
                                        <option ${selval} value="${item.degreeId}" >${item.degreeName}</option>
                                    </c:forEach>
                                </select>
                                <span style="color:red;display:none;" id="degree_error_1">*required</span>
                            </td>
                            <td valign ="top">
                                <span id="span1_qualification_1" >
                                    <select id="qualification_1" name="qualification" style="width:100%">
                                        <option value="" >--Select Qualification--</option>
                                    </select>
                                </span>
                                <span style="display:none" id="span2_qualification_1" ><input type="text" name="qualification1" id="qualification1_1" style="width:100%" /></span>
                                <span style="color:red;display:none;" id="qualification_error_1">*required</span>
                                <div id = "qualificationErr_1" style="display:none;"><font style = "color: red;">Only Alpha Numerics are allowed</font></div>
                            </td>
                            <td valign ="top">
                                <input type="text" id="year_of_passing_1" name="year_of_passing" value="" style="width:100%" onkeypress="return blockNonNumbers(this, event, false, false);" onkeyup="return extractNumber(this, 2, false);" maxlength="4" />
                                <span style="color:red;display:none;" id="year_of_passing_error_1">*required</span>
                                <div id = "yearOfPassing_err_1" style="display:none;"><font style = "color: red;">Enter valid Year</font></div>
                            </td>
                            <td valign ="top">
                                <span id="span1_institution_1" >
                                    <select id="institution_1" name="institution" style="width:100%" onchange="loadTextBox(this.value,1);">
                                        <option value="" >--Select Institution--</option>
                                    </select>
                                </span>
                                <span style="display:none" id="span2_institution_1" ><input type="text" name="institution1" id="institution1_1" style="width:100%" /></span>
                                <span style="color:red;display:none;" id="institution_error_1">*required</span>
                                <div id="other_institution_1" class="other_institution" style="display:none"><label>Institute Name</label>
                                    <input type="text" id="other_institution_tb1" class="" value="${educationData.otherInstitute}" name="otherInstitute"/>
                                    <span style="color:red;display:none;" id="other_institution_error_${EducationStatus.count}" >*required</span>
                                </div>
                                <div id = "institution_err_1" style = "display: none;"><font style = "color:red;">Only Alphanumerics are allowed</font></div>
                            </td>
                            <td valign ="top">
                                <span id="span1_university_1" >
                                    <select id="university_1" name="university" style="width:100%" onchange="loadUniversityText(this.value,1);">
                                        <option value="" >--Select University--</option>
                                    </select>
                                </span>
                                <span style="display:none" id="span2_university_1" ><input type="text" name="university1" id="university1_1" style="width:100%" /></span>
                                <span style="color:red;display:none;" id="university_error_1">*required</span>
                                <div id="other_university_1" class="other_university" style="display:none">
                                    <label>University Name</label>
                                    <input type="text" id="other_university_tb1" class="" value="${educationData.otheruniversity}" name="otheruniversity"/>
                                    <span style="color:red;display:none;" id="other_university_error_${EducationStatus.count}">*required</span>
                                </div>
                                <div id = "university_err_1" style = "display: none;"><font style = "color:red;">Only Alphanumerics are allowed</font></div>
                            </td>
                            <td valign ="top">
                                <input type="text" maxlength="5" id="percentage_1" name="percentage" value="" style="width:100%" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this, 2, false);" />
                                <span style="color:red;display:none;" id="percentage_error_1">*required</span>
                            </td>
                            <td valign ="top"><input type="text" maxlength="50" id="remarks_1" name="remarks" value="" style="width:100%" /></td>
                            <td valign ="top">
                                <input type="hidden" name="attachmentXY" id="attachmentXY_1" value="" />
                                <input type="file" size="8" id="attachment_1" name="attachment_1" ><br>
                                <span style="color:red;display:none;" id="attachment_error_1">*required</span>
                                <div id="attachment_err_1" style="color:red;display: none;">.zip, .jar, .war & .rar files are not accepted</div>
                            </td>
                            </tr>
<!--                            <tr>
                                <td></td><td></td><td></td><td>
                                    <input id ="institutionOthers" name="instituteOthers" type="text" onchange="change(this.value,${institution_1.count});" style="width:100%"/>
                                </td>
                                <td>
                                    <input id ="universityOthers" name ="universityOthers" type="text"onchange="change(this.value,${university_1.count});" style="width:100%"/>
                                </td>
                                <td></td>                                        
                                <td></td><td></td><td></td>
                            </tr> -->
                        </c:if>





                    </table>
                    <table>
                        <tr>
                            <td><input type="button" class="addmore" onclick="addRow_Education();" value="Add"  /></td>
                        </tr>
                    </table>
                    </td>
                    </tr>
                    </table>
                </div>
                <div id="commonforms">
                    <div class="commonformheader">Certifications</div>
                    <table>
                        <tr>
                            <td colspan="3">
                                <c:choose>
                                    <c:when test="${!empty(certificationDetails)}">
                                        <input type="hidden" name="certification_recordCount" id="certification_recordCount" value="${fn:length(certificationDetails)}" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="certification_recordCount" id="certification_recordCount" value="1" />
                                    </c:otherwise>
                                </c:choose>
                                <table width="100%" border="0" cellspacing="5">
                                    <tr class="headerCenter">
                                        <th width="16%"><font color="red"></font>Certification Name</th>
                                        <th width="10%"><font color="red"></font>Date of Passing (eg: 2005) </th>
                                        <th width="20%"><font color="red"></font>Institution</th>
                                        <th width="10%"><font color="red"></font>Percentage/ CGPA</th>
                                        <th width="20%">Remarks</th>
                                        <th width="16%"><font color="red"></font>Proof Attachment</th>
                                        <th width="4%">&nbsp;</th>
                                        <th></th>
                                    </tr>

                                    <c:forEach items="${certificationDetails}" var="CertificationDetails" varStatus="CertificationStatus">
                                        <tr id="CER_TR_${CertificationStatus.count}">
                                        <input type="hidden" name="certificationId" id="certificationId_${CertificationStatus.count}" value="${CertificationDetails.certificationIdX}" />
                                        <input type="hidden" name="CER_TR_deleted" id="CER_TR_deleted_${CertificationStatus.count}" value="0" />
                                        <td valign ="top">
                                            <input type="text" name="cert_qualification" id="cert_qualification_${CertificationStatus.count}" value="${CertificationDetails.cert_qualificationX}" style="width:100%"  />
                                            <span style="color:red;display:none;" id="cert_qualification_error_${CertificationStatus.count}">*required</span>
                                            <div style="color:red;display:none;" id = "cert_qualification_err_${CertificationStatus.count}">Only alphanumerics are allowed</div>
                                        </td>
                                        <td valign ="top">
                                            <input type="text" id="cert_year_of_passing_${CertificationStatus.count}" name="cert_year_of_passing" value="${CertificationDetails.cert_year_of_passingX}" style="width:100%" />
                                            <span style="color:red;display:none;" id="cert_year_of_passing_error_${CertificationStatus.count}">*required</span>
                                            <div style="color:red;display:none;" id = "cert_year_of_passing_err_${CertificationStatus.count}">Enter valid year</div>
                                        </td>
                                        <td valign ="top">
                                            <input type="text" name="cert_institution" id="cert_institution_${CertificationStatus.count}" value="${CertificationDetails.cert_institutionX}" style="width:100%" />
                                            <span style="color:red;display:none;" id="cert_institution_error_${CertificationStatus.count}">*required</span>
                                            <div style="color:red;display:none;" id = "cert_institution_err_${CertificationStatus.count}">Only alphanumerics are allowed</div>
                                        </td>
                                        <td valign ="top">
                                            <input type="text" class="number" maxlength="5" id="cert_percentage_${CertificationStatus.count}" name="cert_percentage" value="${CertificationDetails.cert_percentageX}" style="width:100%" />
                                            <span style="color:red;display:none;" id="cert_percentage_error_${CertificationStatus.count}">*required</span>
                                            <div style="color:red;display:none;" id = "cert_percentage_err_${CertificationStatus.count}">Enter valid percentage</div>
                                        </td>
                                        <td valign ="top">
                                            <input type="text" maxlength="50" id="cert_remarks_${CertificationStatus.count}" name="cert_remarks" value="${CertificationDetails.cert_remarksX}" style="width:100%" />
                                            <span style="color:red;display:none;" id="cert_remarks_error_${CertificationStatus.count}">*required</span>
                                            <div style="color:red;display:none;" id = "cert_remarks_err_${CertificationStatus.count}">Only alphanumerics are allowed</div>
                                        </td>
                                        <td valign ="top">
                                            <input type="file" size="20" id="cert_attachment_${CertificationStatus.count}" name="cert_attachment_${CertificationStatus.count}" /><br />
                                            <span style="color:red;display:none;" id="cert_attachment_error_${CertificationStatus.count}">*required</span>
                                            <div id="cert_attachment_err_${CertificationStatus.count}" style="color:red;display: none;">.zip, .jar, .war & .rar files are not accepted</div>
                                            <c:choose>
                                                <c:when test="${CertificationDetails.cert_attachmentX != '' && CertificationDetails.cert_attachmentX != 'NULL'}">
                                                    <input type="hidden" name="cert_attachmentXY" id="cert_attachmentXY_${CertificationStatus.count}" value="${CertificationDetails.cert_attachmentX}" style="size:8">
                                                    <c:set var="DOCS" value="${fn:split(CertificationDetails.cert_attachmentX, '~~')}" />
                                                    <a href="fileDownload.htm?fileName=${CertificationDetails.cert_attachmentX}"><c:out value="${DOCS[2]}"></c:out></a>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="hidden" name="cert_attachmentXY" id="cert_attachmentXY_${CertificationStatus.count}" value="" >
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td valign="top">
                                            <img src="css/images/delete-icon.png" onclick="deleteRow('CER_TR',${CertificationStatus.count}, 1)" />
                                        </td>

                            </tr>
                        </c:forEach>
                        <c:if test="${empty(certificationDetails)}">
                            <tr id="CER_TR_1">
                            <input type="hidden" name="certificationId" id="certificationId_1" value="" />
                            <input type="hidden" name="CER_TR_deleted" id="CER_TR_deleted_1" value="0" />
                            <td valign ="top">
                                <input type="text" name="cert_qualification" id="cert_qualification_1" style="width:100%" />
                                <span style="color:red;display:none;" id="cert_qualification_error_1">*required</span>
                                <div style="color:red;display:none;" id = "cert_qualification_err_1">Only alphanumerics are allowed</div>
                            </td>
                            <td valign ="top">
                                <input type="text" id="cert_year_of_passing_1" name="cert_year_of_passing" value="" style="width:100%" />
                                <span style="color:red;display:none;" id="cert_year_of_passing_error_1">*required</span>
                                <div style="color:red;display:none;" id = "cert_year_of_passing_err_1">Enter valid year</div>
                            </td>
                            <td valign ="top">
                                <input type="text" name="cert_institution" id="cert_institution_1" style="width:100%" />
                                <span style="color:red;display:none;" id="cert_institution_error_1">*required</span>
                                <div style="color:red;display:none;" id = "cert_institution_err_1">Only alphanumerics are allowed</div>
                            </td>
                            <td valign ="top">
                                <input type="text" class="number" maxlength="5" id="cert_percentage_1" name="cert_percentage" value="" style="width:100%" />
                                <span style="color:red;display:none;" id="cert_percentage_error_1">*required</span>
                                <div style="color:red;display:none;" id = "cert_percentage_err_1">Enter valid percentage</div>
                            </td>
                            <td valign ="top">
                                <input type="text" maxlength="50" id="cert_remarks_1" name="cert_remarks" value="" style="width:100%" />
                                <span style="color:red;display:none;" id="cert_remarks_error_1">*required</span>
                                <div style="color:red;display:none;" id = "cert_remarks_err_1">Only alphanumerics are allowed</div>
                            </td>
                            <td valign ="top">
                                <input type="hidden" name="cert_attachmentXY" value="" id="cert_attachmentXY_1" />
                                <input type="file" style="size:8" id="cert_attachment_1" name="cert_attachment_1" ><br>
                                <span style="color:red;display:none;" id="cert_attachment_error_1">*required</span>
                                <div id="cert_attachment_err_1" style="color:red;display: none;">.zip, .jar, .war & .rar files are not accepted</div>
                            </td>
                            </tr>
                        </c:if>
                    </table>
                    <table>
                        <tr>
                            <td><input type="button" class="addmore" onclick="addRow_Certification();" value="Add"  /></td>
                        </tr>
                    </table>
                    </td>
                    </tr>
                    </table>
                </div>
                <div id="commonforms">
                    <div class="commonformheader">Other Proof Attachment</div>
                    <table border="0" width="100%">
                        <tr>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <table id="educationalQualificationFileAttachment" cellspacing="5">
                                    <tr>
                                        <td>Proof Attachment</td>
                                        <td></td>
                                    </tr>
                                    <c:if test="${empty(eduQaulifiProofDetails)}">
                                        <input type="hidden" id="educationalQualificationFileAttachmentCount" name="educationalQualificationFileAttachmentCount" value="1" />
                                        <tr>
                                            <td style="width:400px;"><input type="file" name="otherProof0" id="otherProof0" value=""  class="" /></td>
                                            <td>
                                                <input type="hidden" name="educationalQualificationFileAttachmentStatus" id="educationalQualificationFileAttachmentStatus1" value="undeleted" />
                                                <input type="hidden" name="jfEduProofId" id="jfEduProofId0" value="" />
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:forEach items="${eduQaulifiProofDetails}" var="eduQualifiProofDetail" varStatus="tsindex">
                                        <input type="hidden" id="educationalQualificationFileAttachmentCount" name="educationalQualificationFileAttachmentCount" value="${fn:length(eduQaulifiProofDetails)}" />
                                        <tr>
                                            <td style="width:400px;">
                                                <c:choose>
                                                    <c:when test="${eduQualifiProofDetail.fileName!=null}">
                                                        <c:set var="splitString" value='${fn:split(eduQualifiProofDetail.fileName,"~~")}'></c:set><a href="fileDownload.htm?fileName=${eduQualifiProofDetail.fileName}&fileType=${eduQualifiProofDetail.fileType}"><c:out value="${splitString[2]}"></c:out></a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="file" name="otherProof${tsindex.index}" id="otherProof0" value="" class="" />
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <input type="hidden" name="educationalQualificationFileAttachmentStatus" id="educationalQualificationFileAttachmentStatus${tsindex.count}" value="undeleted" />
                                                <input type="hidden" name="jfEduProofId" id="jfEduProofId${tsindex.index}" value="${eduQualifiProofDetail.jfEduProofIdDb}" />
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <table cellspacing="5">
                                    <tr>
                                        <c:if test="${printStatus!='yes'}">
                                            <td><input type="button" class="addmore" onclick="checkEmptyValueForProof('educationalQualificationFileAttachmentCount', 'otherProof', 'educationalQualificationFileAttachment', 'jfEduProofId', 'Proof', 'educationalQualificationFileAttachmentStatus')" value="Add"  /></td>
                                            </c:if>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" align="center" width="100%">
                                <c:if test="${printStatus!='yes'}">
                                    <input type="button" class="backbutton" name="backButton" value="Back" onclick="redirectBack()">
                                    <input type="submit" id="continuebutton1" class="continuebutton"  value="Save &amp; Continue" >
                                </c:if>
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
            <form action="joineraddstepone.htm?trackNumber=${trackNumber}" method="POST" name="backForm" id="backForm">
                <input type="hidden" name="backButton" value="Back">
            </form>
    </div>
    <script type="text/javascript">
    $(document).ready(function() {
        for (i = 1; i <= $('#skill_recordCount').val(); i++) {
            $("#stream_" + i).html($("#streamFirst").html());
            var q = $('#streamSelected_' + i).val();
            $("#stream_" + i).val(q);
            changeSkill(q, i);
        }

        for (var k = 1; k <= $('#education_recordCount').val(); k++) {
            if (($('#degree_' + k).val() != 10 && $('#degree_' + k).val() != 12)) {
                $("#qualification_" + k).html($("#qualificationFirst").html());
                $("#institution_" + k).html($("#institutionFirst").html());
                $("#university_" + k).html($("#universityFirst").html());
            }

            if (document.getElementById('qualificationSelected_' + k) != null) {
                var q = $('#qualificationSelected_' + k).val();
                $("#qualification_" + k).val(q);
            }
            if (document.getElementById('institutionSelected_' + k) != null) {
                var r = $('#institutionSelected_' + k).val();
                $("#institution_" + k).val(r);
            }
            if (document.getElementById('universitySelected_' + k) != null) {
                var s = $('#universitySelected_' + k).val();
                $("#university_" + k).val(s);
            }
        }
    });

    function checkEmptyValueForProof(countId, mandatoryValueId, tableName, empId, errorMessage, deleteStatusId) {
        var count = document.getElementById(countId).value;
        var deleteStatus = document.getElementById(deleteStatusId + count).value;
        if (document.getElementById(count) && document.getElementById(+count).value.length == 0 && deleteStatus != 'deleted') {
            alert("Upload " + errorMessage + " to add more rows");
            document.getElementById(madatoryValueId + count).focus();
        } else {
            addRow(tableName, empId);
        }
    }
    
    function loadTextBox(value,rowId){
        var insti = value;
        var rowid = rowId;
        if(Number(insti) == 1205){
            $("#other_institution_"+rowid).css("display","block");
        }else{
            $("#other_institution_"+rowid).css("display","none");
        }
    }
        function loadUniversityText(value,rowId){
            var univ = value;
            var rowid = rowId;
            if(Number(univ)==605){
                $("#other_university_"+rowid).css("display","block");
            }else{
                $("#other_university_"+rowid).css("display","none");
            }
            
        }
    
    </script>
</body>
