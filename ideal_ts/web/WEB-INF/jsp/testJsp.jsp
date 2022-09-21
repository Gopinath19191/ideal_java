<%@include file="../../../../header.jsp"  %>
<title>Joining Formalities - Initiation Process</title>

<script type="text/javascript">

    $(document).ready(function() {
        $("#dateOfBirth").datepicker({
            changeYear: true,
            changeMonth: true,
            dateFormat:'dd-mm-yy',
            showButtonPanel: true,
            minDate: "-60Y", maxDate: "-18Y"
        });

        $("#employeeAddForm").validate({
            errorElement:"div",
            errorClass:"customError"
        });
        <c:if test="${fn:length(reportingManagerObj)!=0}">
           var reportingManagerData = eval(<json:array name="items" var="myemployeeData" items="${reportingManagerObj}">
                <json:object>
                  <json:property name="id" value="${myemployeeData.empId}"/>
                  <json:property name="value" value="${myemployeeData.employeeName}-${myemployeeData.employeeNumber}"/>
                </json:object>
            </json:array>);
        </c:if>
        <c:if test="${fn:length(emprefObj)!=0}">
           var emprefdata = eval(<json:array name="items" var="myemployeeData" items="${emprefObj}">
                <json:object>
                  <json:property name="id" value="${myemployeeData.empId}"/>
                  <json:property name="value" value="${myemployeeData.employeeName}-${myemployeeData.employeeNumber}"/>
                </json:object>
            </json:array>);
        </c:if>
        <c:if test="${fn:length(contractEmployeeObj)!=0}">
           var contractempdata = eval(<json:array name="items" var="contractEmployeeData" items="${contractEmployeeObj}">
                <json:object>
                  <json:property name="id" value="${contractEmployeeData.empId}"/>
                  <json:property name="value" value="${contractEmployeeData.employeeName}-${contractEmployeeData.employeeNumber}"/>
                </json:object>
            </json:array>);
        </c:if>
                $("#rmName").autoSuggest('${pageContext.request.contextPath}/com/defiance/ideal/joiningForm/getEmployeeName.do', {selectedItemProp: "value",asHtmlID:"rmName", searchObjProps: "value",selectionLimit:1,selectedValuesProp:"id"<c:if test="${fn:length(reportingManagerObj)!=0}">,preFill:reportingManagerData</c:if>});
                $('#as-values-rmName').attr("class","required");

                $("#empref").autoSuggest('${pageContext.request.contextPath}/com/defiance/ideal/joiningForm/getEmployeeName.do', {selectedItemProp: "value",asHtmlID:"empref", searchObjProps: "value",selectionLimit:1,selectedValuesProp:"id"<c:if test="${fn:length(emprefObj)!=0}">,preFill:emprefdata</c:if>});
                $('#as-values-empref');

                $("#empContract").autoSuggest('${pageContext.request.contextPath}/com/defiance/ideal/joiningForm/getEmployeeNamebyContract.do', {selectedItemProp: "value",asHtmlID:"empContract", searchObjProps: "value",selectionLimit:1,selectedValuesProp:"id"<c:if test="${fn:length(contractEmployeeObj)!=0}">,preFill:contractempdata</c:if>});

        <c:if test="${fn:length(mailCcList)!=0}">
           var mailCcData = eval(<json:array name="items" var="mailCcValues" items="${mailCcList}">
                <json:object>
                  <json:property name="id" value="${mailCcValues.empId}"/>
                  <json:property name="value" value="${mailCcValues.employeeName}-${mailCcValues.employeeNumber}"/>
                </json:object>
            </json:array>);
        </c:if>
        $("#mailCc").autoSuggest('${pageContext.request.contextPath}/com/defiance/ideal/joiningForm/getEmployeeNamebyhr.do', {selectedItemProp: "value",asHtmlID:"mailCc", searchObjProps: "value",selectionLimit:2,selectedValuesProp:"id"<c:if test="${fn:length(mailCcList)!=0}">,preFill:mailCcData</c:if>});
        $('#as-values-mailCc').attr("class","required");

    });
    
    function changeStructureDesc(selectedValue,url,outputId,outputDisplayText,outputOptValue,outputOptText,selectedId){
         if(selectedValue != ""){
           $.ajax({
                url: url,
                type: "POST",
                async: false,
                data: ({structureId:selectedValue}),
                success: function(ajaxObj) {
                    var returnData = eval(ajaxObj);
                    var arrayLength = returnData.length;
                    var selectObj = document.getElementById(outputId);
                    selectObj.length=0;
                    $('#'+outputId).append($("<option></option>").attr("value","").text(outputDisplayText));
                    for(counter = 0; counter<arrayLength;counter++){
                    $('#'+outputId).append($("<option></option>").attr("value",returnData[counter][outputOptValue]).text(returnData[counter][outputOptText]));
                    }
                }
            });
        }
     }

     function loadRRF(selectedValue,jfId){
         if(selectedValue != ""){
           $.ajax({
                url: "./loadRRF.do",
                type: "POST",
                async: false,
                data: ({practiceGroup:selectedValue,jf_id:jfId}),
                success: function(ajaxObj) { 
                    var arr = ajaxObj.split(":");
                    var mySelect = document.getElementById("rrf_id");
                    mySelect.options.length = 0;
                    mySelect.options[0] = new Option ("--Select RRF Id--","");
                    var count=1;
                    for(var i=0;i<arr.length;i++) {
                        var out = arr[i].split(",");
                        if(out!='') {
                            if($('#selectedRRF').val() !="" && $('#selectedRRF').val() == out[0] ) {
                                document.getElementById("rrf_id").options[count+i] = new Option (out[1],out[0]);
                                document.getElementById("rrf_id").options[count+i].selected = true;
                            } else {
                                document.getElementById("rrf_id").options[count+i] = new Option (out[1],out[0]);
                            }
                        }
                    }
                }
            });
        }
     }

     function checkEmail(selectedValue) {
        if(selectedValue != ""){
           $.ajax({
                url: '${pageContext.request.contextPath}/com/defiance/ideal/joiningForm/checkExistingEmail.do',
                type: "POST",
                async: false,
                data: ({email1:selectedValue}),
                success: function(ajaxObj) {
                    var returnData = eval(ajaxObj);
                    if(returnData==1){
                        document.getElementById('personalEmail').onfocus;
                        alert("Emailid already exists.");
                        return false;
                    } else {
                        return true;
                    }
                }
            });
        }
     }

     function validatation() {
         if(document.getElementById("passportNumber").value == '' && document.getElementById("PanNo").value == '') {
             $('#passportRequired').show();
             document.getElementById("passportNumber").focus();
             return false;
         } else {
             $('#passportRequired').hide();
             return true;
         }
     }

     function backToList() {
         window.location.href="${pageContext.request.contextPath}/com/defiance/ideal/joiningForm/begin.do";
     }
     
     function fndispage() {
         if(document.getElementById("sourceofhire").value=='d') {
             $("#direct").show();
             $("#referral").hide();
             $("#vendor").hide();
             $("#jobportal").hide();
             $('#as-values-empref').attr('class', '');
             $('#vendorname').attr('class', '');
             $('#jobporname').attr('class', '');
         } else if(document.getElementById("sourceofhire").value=='r') {
             $("#direct").hide();
             $("#referral").show()
             $("#vendor").hide();
             $("#jobportal").hide();
             $('#as-values-empref').attr('class', 'required');
             $('#vendorname').attr('class', '');
             $('#jobporname').attr('class', '');
         } else if(document.getElementById("sourceofhire").value=='v') {
             $("#direct").hide();
             $("#referral").hide();
             $("#vendor").show();
             $("#jobportal").hide();
             $('#as-values-empref').attr('class', '');
             $('#vendorname').attr('class', 'required');
             $('#jobporname').attr('class', '');
         } else if(document.getElementById("sourceofhire").value=='j') {
             $("#direct").hide();
             $("#referral").hide();
             $("#vendor").hide();
             $("#jobportal").show();
             $('#as-values-empref').attr('class', '');
             $('#vendorname').attr('class', '');
             $('#jobporname').attr('class', 'required');
         }
         if(document.getElementById("sourceofhire").value=='') {
             $("#direct").hide();
             $("#referral").hide();
             $("#vendor").hide();
             $("#jobportal").hide();
         }
     }

    function existingEmployee() {
        if($('#contract_type').val() == "e") {
            $('#divEmpContract').show();
            $('#as-values-empContract').attr('class', 'required');
            $('#rrf_id').attr('class', '');
        } else {
            $('#divEmpContract').hide();
            $('#rrf_id').attr('class', 'required');
        }
    }
     
</script>
        <c:if test="${!empty(recruitmentData.levelOneStruct)}">
        <c:set var="onloadFn" value="changeStructureDesc(${recruitmentData.levelOneStruct})"></c:set>
        <c:set var="structureId" value="${recruitmentData.levelOneStruct}"></c:set>
        </c:if>
        <c:if test="${!empty(employeeData.levelOneStruct)}">
        <c:set var="onloadFn" value="changeStructureDesc(${employeeData.levelOneStruct})"></c:set>
        <c:set var="structureId" value="${employeeData.levelOneStruct}"></c:set>
        </c:if>
<body class="ext-gecko ext-gecko3" onload="<c:out value="${onloadFn}" />">
    
    <div id="container">
        <%@include file="../../../../menu.jsp" %>
        <div class="notice_page">
            <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/css/images/icon_alert.png" /></div>
            <div style="padding-left:20px;">
                <ul class="notice_page_ul">
                    <li>Fields marked in <font color="red" size="3">*</font> are mandatory</li>
                    <li>RRF Id mandatory for new employee creation. For DC / VC RRF Id not mandatory</li>
                </ul>
            </div>
        </div>
        <div id="commonforms">
            <div class="commonformheader" style="padding-left:15px;">Recruiter Screen</div>
            <div align="center">
                <form name="employeeAddForm" id="employeeAddForm" action="addOrUpdateEmployee.do" method="post" >
                    <c:choose>
                        <c:when test="${canStatus>=1}">
                            <c:set value="disabled" var="disableStatus" />
                        </c:when>
                        <c:otherwise>
                            <c:set value="" var="disableStatus" />
                        </c:otherwise>
                    </c:choose>
                    <table width="99%" border="0" align="center" cellspacing="5" >
                        <tr>
                            <td style="width:33%">
                                <label for="firstName" class="">First Name<font color="red" size="4px;">*</font></label>
                                <input type="text" name="firstName" value ="${candidateDetails.firstName}" id="firstName" class="required alphaWithDot" ${disableStatus} />
                            </td>
                            <td style="width:33%">
                                <label for="middleName">Middle Name</label>
                                <input type="text" name="middleName" value ="${candidateDetails.middleName}" id="middleName" class="alphaWithDot" ${disableStatus} />
                            </td>
                            <td style="width:33%">
                                <label for="lastName" class="">Last Name<font color="red" size="4px;">*</font></label>
                                <input type="text" name="lastName" value ="${candidateDetails.lastName}" id="lastName2" class="required alphaWithDot" ${disableStatus} />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="companyStruct" class="">Company Structure<font color="red" size="4px;">*</font></label>
                                <select name="structureName" id="companyStruct" class="required" onchange="changeStructureDesc(this.value,'./getCompanyStructure.do','sbu','--Select--','structureId','structureName',this.id)" ${disableStatus} >
                                    <option value="">--Select--</option>
                                    <c:forEach items="${levelOneStructure}" var="cs1">
                                        <c:choose>
                                            <c:when test="${candidateDetails.structureId==cs1.structureId}">
                                                <option value="${cs1.structureId}" selected="selected">${cs1.structureName}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${cs1.structureId}">${cs1.structureName}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <label for="sbu" class="" class="required">Practice Group<font color="red" size="4px;">*</font></label>
                                <select name="practiceGroup" id="sbu" class="required" onchange="changeStructureDesc(this.value,'./getCompanyStructure.do','subSbu','--Select--','structureId','structureName',this.id);loadRRF(this.value);" ${disableStatus}>
                                    <option value="">--Select--</option>
                                    <c:forEach items="${practiceGroupList}" var="sbu">
                                        <c:choose>
                                            <c:when test="${sbu.practiceGroupId == candidateDetails.practiceGroupId}">
                                                <option value="${sbu.practiceGroupId}" selected="selected" >${sbu.practiceGroup}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${sbu.practiceGroupId}">${sbu.practiceGroup}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <label for="subSbu" class="" class="required">Sub Practice Group<font color="red" size="4px;">*</font></label>
                                <select name="subPracticeGroup" id="subSbu" class="required" ${disableStatus}>
                                    <option value="">--Select--</option>
                                    <c:forEach items="${subPracticeGroupList}" var="subSbu">
                                        <c:choose>
                                            <c:when test="${subSbu.subPracticeGroupId == candidateDetails.subPracticeGroupId}">
                                                <option value="${subSbu.subPracticeGroupId}" selected="selected">${subSbu.subPracticeGroup}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${subSbu.subPracticeGroupId}">${subSbu.subPracticeGroup}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                         <tr>
                            <td>
                                <label for="band" class="">Band<font color="red" size="4px;">*</font></label>
                                <select name="band" id="band" class="required" onchange="changeStructureDesc(this.value,'./getSubDetails.do','subBand','--Select--','bandId','bandName',this.id)" ${disableStatus} >
                                    <option value="">--Select--</option>
                                    <c:forEach items="${bandList}" var="bands">
                                        <c:choose>
                                            <c:when test="${candidateDetails.bandId==bands.bandId}">
                                                <option value="${bands.bandId}" selected="selected">${bands.bandName}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${bands.bandId}">${bands.bandName}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <label for="subBand" class="" class="required">Sub Band<font color="red" size="4px;">*</font></label>
                                <select name="subBand" id="subBand" class="required" ${disableStatus}>
                                    <option value="">--Select--</option>
                                    <c:forEach items="${subBandList}" var="subBands">
                                        <c:choose>
                                            <c:when test="${subBands.bandId == candidateDetails.subBandId}">
                                                <option value="${subBands.bandId}" selected="selected">${subBands.bandName}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${subBands.bandId}">${subBands.bandName}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <label for="designation" class="" class="required">Designation<font color="red" size="4px;">*</font></label>
                                <select name="designation" id="designation" class="required" ${disableStatus}>
                                    <option value="">--Select--</option>
                                    <c:forEach items="${designationList}" var="designation">
                                        <c:choose>
                                            <c:when test="${designation.desigId == candidateDetails.desigId}">
                                                <option value="${designation.desigId}" selected="selected">${designation.desigName}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${designation.desigId}">${designation.desigName}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="rmName" class="" >Name of Reporting Manager<font color="red" size="4px;">*</font></label>
                                <c:if test="${canStatus==0 || canStatus==null}">
                                    <input type="text" name="reportingManager" value ="${candidateDetails.reportingManager}" id="rmName" ${disableStatus} />
                                </c:if>
                                <c:if test="${canStatus>=1}">
                                    ${reportingManagerName}
                                </c:if>
                            </td>
                            <td>
                                <label for="employeeType" class="">Employee Type<font color="red" size="4px;">*</font></label>
                                <select name="employeeType" id="employeeType" class="required" ${disableStatus}>
                                    <option value="">--Select--</option>
                                    <c:forEach items="${employeeTypeList}" var="employeeTypeValues" varStatus="index">
                                        <c:choose>
                                            <c:when test="${candidateDetails.employeeType == employeeTypeValues.key}">
                                                <option value="${employeeTypeValues.key}" selected="selected">${employeeTypeValues.value}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${employeeTypeValues.key}">${employeeTypeValues.value}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <label for="dateOfBirth" class="">Date Of Birth<font color="red" size="4px;">*</font></label>
                                <input type="text" class="required" value ="${candidateDetails.dateOfBirth}" name="dateOfBirth" id="dateOfBirth" readonly ${disableStatus} />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="personalEmail" class="">Personal email 1<font color="red" size="4px;">*</font></label>
                                <%--<input type="text" name="personalEmailId1" value ="${candidateDetails.personalEmailId1}" id="personalEmail" class="required email" ${disableStatus} onblur="return checkEmail(this.value);" />--%>
                                <input type="text" name="personalEmailId1" value ="${candidateDetails.personalEmailId1}" title="${candidateDetails.personalEmailId1}" id="personalEmail" class="required email" ${disableStatus} />
                            </td>
                            <td>
                                <label for="personalEmail">Personal email 2</label>
                                <input type="text" name="personalEmailId2" value ="${candidateDetails.personalEmailId2}" title="${candidateDetails.personalEmailId2}" id="email_id2" class="email" ${disableStatus} />
                            </td>

                            <td>
                                <label for="passportNumber" class="">Passport number<font color="red" size="4px;">*</font></label>
                                <input type="hidden" value="${passportArr.passportIdX}" name="passport_id" />
                                <input type="text" name="passport_number" value ="${passportArr.passportNumberX}" title="${passportArr.passportNumberX}" id="passportNumber" class="alphanumeric" maxlength="20" ${disableStatus} />
                                <div class="customError" id="passportRequired" style="display:none">Enter Either Passport or Pan Number</div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="PanNo" class="">PAN No<font color="red" size="4px;">*</font></label>
                                <input type="text" name="panNo" value ="${candidateDetails.panNo}" title="${candidateDetails.panNo}" id="PanNo" class="alphanumeric" maxlength="20" ${disableStatus} />
                            </td>
                            <td>
                                <label for="Sourceofhire" class="">Source of Hire<font color="red" size="4px;">*</font></label>
                                <select name="sourceofhire" id="sourceofhire" class="required" onchange="fndispage()" ${disableStatus}>
                                    <option value="">--Select--</option>
                                    <c:forEach items="${sourceofhirelist}" var="sourceofhirelist" >
                                        <option <c:if test="${candidateDetails.sourceofhire==sourceofhirelist.sorceconfkey}">selected="true"</c:if> value="${sourceofhirelist.sorceconfkey}"> ${sourceofhirelist.sourceconfval}</option>
                                </c:forEach>
                                </select>
                            </td>

                             <td id="direct" style="display: none;">
                             </td>
                             <td id="referral" style="display: none;"><label for="empref" class="">Employee Referral<font color="red" size="4px;">*</font></label>

                                 <c:if test="${canStatus==0 || canStatus==null}">
                                     <input type="text" name="empref" id="empref" value="${candidateDetails.empref}" ${disableStatus} />
                                </c:if>
                                <c:if test="${canStatus>=1}">
                                    ${emprefname}
                                </c:if>
                              </td>

                              <td id="vendor" style="display: none;"><label for="vendorname" class="">Add Vendor<font color="red" size="4px;">*</font></label>
                               <select name="vendorname" id="vendorname" ${disableStatus}>
                                    <option value="">--Select--</option>
                                     <c:forEach items="${vendorList}" var="vendorlist">
                                           <option <c:if test="${candidateDetails.empref==vendorlist.vendorId}">selected="true"</c:if> value="${vendorlist.vendorId}">${vendorlist.vendorName}</option>
                                    </c:forEach>
                                </select></td>
                                <td id="jobportal" style="display: none;"><label for="jobporname" class="">Job Portal<font color="red" size="4px;">*</font></label>
                                <select name="jobporname" id="jobporname" ${disableStatus}>
                                    <option value="">--Select--</option>
                                    <c:forEach items="${portallistlist}" var="portallistlist">
                                           <option <c:if test="${candidateDetails.empref==portallistlist.jobconfkey}">selected="true"</c:if> value="${portallistlist.jobconfkey}">${portallistlist.jobconfval}</option>
                                    </c:forEach>
                                </select></td>
                        </tr>
                        <tr>
                            <c:if test="${buttonStatus=='triggerMail' || canStatus>=1 || buttonStatus=='editMode'}">
                                <td>
                                    <label for="refnumber" >Candidate Ref Number</label>
                                    <input type="text" name="refnumber" readonly value ="${candidateDetails.refnumber}" title="${candidateDetails.refnumber}" id="refnumber" ${disableStatus} />
                                </td>
                                <c:if test="${buttonStatus=='triggerMail' || canStatus>=1}">
                                    <td><label for="mailTo" class="">Mail To<font color="red" size="4px;">*</font></label>
                                        <input type="text" name="mailTo" id="mailTo" value="${candidateDetails.personalEmailId1},${candidateDetails.personalEmailId2}" ${disableStatus} title="${candidateDetails.personalEmailId1},${candidateDetails.personalEmailId2}"/></td>
                                    <td><label for="mailCc" class="">Mail CC<font color="red" size="4px;">*</font></label>
                                        <c:if test="${canStatus==0}">
                                            <input type="text" name="mailCc" id="mailCc" value="" ${disableStatus} />
                                        </c:if>
                                        <c:if test="${canStatus>=1}">
                                            ${hrMailCCNames}
                                        </c:if>
                                    </td>
                                    </c:if>
                                </c:if>
                        </tr>
                        <tr>
                            <td valign="top">
                                <label class="">RRF Id<font color="red" size="4px;">*</font></label>
                                <input type="hidden" id="selectedRRF" value="${candidateDetails.rrfRes}" />
                                <select class="" name="rrf_id" id="rrf_id" ${disableStatus}>
                                    <option value="">--Select RRF Id--</option>
                                </select>
                            </td>
                            <td valign="top">
                                <label class="">Employee Type<font color="red" size="4px;">*</font></label>
                                <select name="contract_type" id="contract_type" class="required" onchange="existingEmployee()" ${disableStatus}>
                                    <option value="n" ${candidateDetails.employeeCategory=='n'?'selected':''} >New</option>
                                    <option value="e" ${candidateDetails.employeeCategory=='e'?'selected':''} >DC / VC</option>
                                </select>
                            </td>

                            <td>
                                <div id="divEmpContract" style="display:${candidateDetails.employeeCategory == "e"?'block':'none'};">
                                    <c:choose>
                                        <c:when test="${canStatus==0 || canStatus==null}">
                                            <label class="">DC / VC<font color="red" size="4px;">*</font></label>
                                            <input type="text" name="empContract" id="empContract" ${disableStatus} />
                                        </c:when>
                                        <c:otherwise>
                                            <label class="">DC / VC<font color="red" size="4px;">*</font></label>
                                            ${contractEmployeeName}
                                        </c:otherwise>
                                    </c:choose>


                                </div>
                            </td>
                        </tr>
                        <tr><td></td></tr>
                        <c:if test="${buttonStatus=='triggerMail'}">
                            <tr>
                                <td colspan="3" align="center">
                                    <input type="submit" class="submitbutton" name="buttonName" id="mailTrigger"  value="${triggerMail}" onclick="return validatation();" ${disableStatus} >
                                    <input type="button" class="cancelbutton" name="buttonName" value="${cancelButton}" onclick="backToList();" >
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${buttonStatus=='editMode' || buttonStatus==null}">
                            <tr>
                                <td colspan="3" align="center">
                                    <input type="submit" class="submitbutton" name="buttonName" value="${submitButton}" onclick="return validatation();" ${disableStatus}>
                                    <input type="button" class="cancelbutton" value="${cancelButton}" onclick="backToList();" >
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <td colspan="3">
                                <input type="hidden" name="jfId" value ="${candidateDetails.jfId}" id="jfId"/>
                                <input type="hidden" name="status" value ="${candidateDetails.status}" id="currentStatus"/>
                                <input type="hidden" name="trackNumber" value ="${candidateDetails.trackNumber}" id="trackNumber"/>
                                &nbsp;
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>

    <script>
        $(document).ready(function() {
            fndispage();
            existingEmployee();
            var pg = $('#sbu').val();
            var jfId = $('#jfId').val();
            loadRRF(pg,jfId);
        });
    </script>