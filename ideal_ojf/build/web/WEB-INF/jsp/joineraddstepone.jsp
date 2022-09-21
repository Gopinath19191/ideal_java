<%@include file="header.jsp" %>
<%@include file="joineraddcommonscripts.jsp" %>
<link rel="stylesheet" href="javascript/jquery.superbox.css" type="text/css" media="all" />
<script type="text/javascript" src="javascript/jquery.superbox-min.js"></script>
<title>Employee Details - Step 1 of 4</title> 
<script type="text/javascript">
    $(document).ready(function() {
        $("#dateOfBirth").datepicker({
            changeYear: true,
            changeMonth: true,
            dateFormat: 'dd-mm-yy',
            showButtonPanel: false,
            yearRange: '1900:'+new Date().getFullYear()
//            minDate: "-60Y", maxDate: "-18Y",
//            onSelect: function(dateText, inst) {
//                var selectedDate = dateText.split("-");
//                $("#dateOfMarriage").datepicker("option", "minDate", new Date(parseInt(selectedDate[2]) + 18, --selectedDate[1], selectedDate[0]));
//            }
        });

        $("#dateOfMarriage").datepicker({
            changeYear: true,
            changeMonth: true,
            dateFormat: 'dd-mm-yy',
            showButtonPanel: false,
            yearRange: '1900:'+new Date().getFullYear()
//            maxDate: "0"
        });

        $("#idealJavaForm").validate({
            errorElement: "div",
            errorClass: "customError"
        });
        $(function() {
            $.superbox();
        });
    });
    function setDOM()
    {
        if (document.getElementById('dateOfBirth') && document.getElementById('dateOfBirth').value != "")
        {
            var selectedDate = document.getElementById('dateOfBirth').value.split("-");
            $("#dateOfMarriage").datepicker("option", "minDate", new Date(parseInt(selectedDate[2]), --selectedDate[1], selectedDate[0]));
        }
    }
    function hideErrorMsg()
    {
        $('#errorMsg').hide();
    }
    function bankValidation() {
        var accType = document.getElementById('bankAccType').value;
        if (accType == '0') {
            if (document.getElementById('nameInBankRecords').value == '') {
                $('#nameInBankRequired').show();
                document.getElementById('nameInBankRecords').focus();
                return false;
            }
            if (document.getElementById('bankName').value == '') {
                $('#bankNameRequired').show();
                document.getElementById('bankName').focus();
                return false;
            }
            if (document.getElementById('accountNumber').value == '') {
                $('#accountNumberRequired').show();
                document.getElementById('accountNumber').focus();
                return false;
            }
//            if (document.getElementById('ifsc').value == '') {
//                $('#ifscCodeRequired').show();
//                document.getElementById('ifsc').focus();
//                return false;
//            }
            if (document.getElementById('confirmAccountNumber').value == '') {
                $('#confirmAccountNumberRequired').show();
                document.getElementById('confirmAccountNumber').focus();
                return false;
            }
            if (document.getElementById('branch').value == '') {
                $('#branchRequired').show();
                document.getElementById('branch').focus();
                return false;
            }
//            if (document.getElementById('bankCountry').value == '') {
//                $('#bankCountryRequired').show();
//                document.getElementById('bankCountry').focus();
//                return false;
//            }
//            if (document.getElementById('bankRegion').value == '') {
//                $('#bankRegionRequired').show();
//                document.getElementById('bankRegion').focus();
//                return false;
//            }
//            if (document.getElementById('bankCity').value == '') {
//                $('#bankCityRequired').show();
//                document.getElementById('bankCity').focus();
//                return false;
//            }
            if (!checkAccountNumber(document.getElementById('confirmAccountNumber').value, document.getElementById('accountNumber').value)) {
                return false;
            }
        }

        if (document.getElementById('empPhotoUpload').value != "") {
            var fileName = document.getElementById('empPhotoUpload').value;
            if (fileName != null) {
                var dot = fileName.lastIndexOf(".");
                var extension = fileName.substr(dot, fileName.length);
                if (extension != '.jpg' && extension != '.jpeg' && extension != '.JPG' && extension != '.JPEG') {
                    alert("Kindly Upload only JPG/JPEG format");
                    return false;
                }
            }
        }
        
        if (document.getElementById('adharNo').value != "") {
          
            var adharNo = document.getElementById('adharNo').value;
            if (adharNo.match(/[a-z]/i) || adharNo.match(/[^\w\s]/gi) || (adharNo.length!=12)) {
               $('#adharNameRequired').show();
                document.getElementById('adharNo').focus();
                return false;
               
            }
        }
        
        if (document.getElementById('panNo').value != "") {
          
            var panNo = document.getElementById('panNo').value;
            var regpan = /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/;
            if (regpan.test(panNo)) {
              // alert("valid pan");
            }else{
                $('#panNameRequired').show();
                document.getElementById('panNo').focus();
                return false;
            }
        }
    }
    function getMaritalStausOthers(maritalStatusValue) {
        if (maritalStatusValue != 'm') {
            document.getElementById('dateOfMarriage').disabled = "true";
            document.getElementById('spouseName').disabled = "true";
        }
        if (maritalStatusValue == 'm') {
            document.getElementById('dateOfMarriage').disabled = "";
            document.getElementById('spouseName').disabled = "";
        } else {
            document.getElementById('dateOfMarriage').value = "";
            document.getElementById('spouseName').value = "";
        }
    }
    function hideBankDetails(accountType, bankFlag) {
        if (accountType == 0) {
            document.getElementById('bankDetailsTd').style.display = "block";
            if (bankFlag == 0) {
                document.getElementById('bankProofYes').style.display = "block";
                document.getElementById('bankProofNo').style.display = "none";
            } else if (bankFlag == 1) {
                document.getElementById('bankProofYes').style.display = "none";
                document.getElementById('bankProofNo').style.display = "block";
            }
        } else {
            document.getElementById('nameInBankRecords').value = "";
            document.getElementById('bankName').value = "";
            document.getElementById('branch').value = "";
            document.getElementById('bankCity').value = "";
            document.getElementById('accountNumber').value = "";
            document.getElementById('confirmAccountNumber').value = "";
            document.getElementById('bankDetailsTd').style.display = "none";
            document.getElementById('bankProofYes').style.display = "block";
            document.getElementById('bankProofNo').style.display = "none";
        }
        return true;
    }

    function bankDetails()
    {
        var bankType = document.getElementById('bankAccType').value;
        if (bankType != "")
        {
            return hideBankDetails(bankType, 0);
        }
    }

    function loadState(val, region) {
        if (region == 'region')
            var bankVal = 'bankRegion';
        else
            var bankVal = 'bankCity';
        $.post('loadState.htm', {
            country_id: val,
            region: region
        },
        function(ajaxObj) {
            var arr = ajaxObj.split(":");
            var mySelect = document.getElementById(bankVal);
            mySelect.options.length = 0;
            if (region == 'region')
                mySelect.options[0] = new Option("--Select Region--", "");
            else
                mySelect.options[0] = new Option("--Select City--", "");
            var count = 1;
            for (var i = 0; i < arr.length; i++) {
                var out = arr[i].split(",");
                if (out != '') {
                    document.getElementById(bankVal).options[count + i] = new Option(out[1], out[0]);
                }
            }
        }
        );
    }

    function loadState1(val, region, row_id) {
        var rowName;
        if (region == 'region') {
            rowName = 'region_id_' + row_id;
            $('#city_id_' + row_id).val("");
        } else {
            rowName = 'city_id_' + row_id;
        }
        $.post('loadState.htm', {
            country_id: val,
            region: region
        },
        function(ajaxObj) {
            //console.log("ajaxObj:" + ajaxObj)
            var arr = ajaxObj.split(":");
            var mySelect = document.getElementById(rowName);
            mySelect.options.length = 0;
            if (region == 'region')
                mySelect.options[0] = new Option("-- Region --", "");
            else
                mySelect.options[0] = new Option("-- City --", "");
            var count = 1;
            for (var i = 0; i < arr.length; i++) {
                var out = arr[i].split(",");
                if (out != '') {
                    if ($('#selected_' + rowName).val() == out[0]) {
                        document.getElementById(rowName).options[count + i] = new Option(out[1], out[0]);
                        document.getElementById(rowName).options[count + i].selected = true;
                    } else {
                        document.getElementById(rowName).options[count + i] = new Option(out[1], out[0]);
                    }
                }
            }
        }
        );
    }
    
    function attachmentValidation(){
        if (document.getElementById('empAddProof').value != "") {
            var fileName = document.getElementById('empAddProof').value;
            if (fileName != null) {
                var dot = fileName.lastIndexOf(".");
                var extension = fileName.substr(dot, fileName.length);
                if (extension == '.zip' || extension == '.rar' || extension == '.jar' || extension == '.war') {
                    $('#empAddProof_err').show();
                    return false;
                }else{
                    $('#empAddProof_err').hide();                   
                    return true;
                }
            }
        }
        if (document.getElementById('empPhotoUpload').value != "") {
            var fileName = document.getElementById('empPhotoUpload ').value;
            if (fileName != null) {
                var dot = fileName.lastIndexOf(".");
                var extension = fileName.substr(dot, fileName.length);
                if (extension == '.zip' || extension == '.rar' || extension == '.jar' || extension == '.war') {
                    $('#empPhoto_err').show();
                    return false;
                }else{
                    $('#empPhoto_err').hide();                   
                    return true;
                }
            }
        }
        
        if (document.getElementById('empSignatureFile').value != "") {
            var fileName = document.getElementById('empSignatureFile ').value;
            if (fileName != null) {
                var dot = fileName.lastIndexOf(".");
                var extension = fileName.substr(dot, fileName.length);
                if (extension == '.zip' || extension == '.rar' || extension == '.jar' || extension == '.war') {
                    $('#empSignature_err').show();
                    return false;
                }else{
                    $('#empSignature_err').hide();                   
                    return true;
                }
            }
        }
        
        if (document.getElementById('empAdharProof').value != "") {
            var fileName = document.getElementById('empAdharProof').value;
            if (fileName != null) {
                var dot = fileName.lastIndexOf(".");
                var extension = fileName.substr(dot, fileName.length);
                if (extension == '.zip' || extension == '.rar' || extension == '.jar' || extension == '.war') {
                    $('#empAdharProof_err').show();
                    return false;
                }else{
                    $('#empAdharProof_err').hide();
                     $("#continuebutton0").hide();
                    return true;
                }
            }
        }
        
        
    }

</script>
<style type="text/css">
    form label {
        width: 35%;
        color: #666666;
    }
</style>
<body class="ext-gecko ext-gecko3" onload="bankDetails();" >
    <div id="container">
        <%@include file="menu.jsp" %>
        <div class="LabelHeader">Employee Details - Step 1 of 4</div>
        <div class="notice_page">
            <div style="float:left;"><img alt="Information" title="Information" src="css/images/icon_alert.png"/></div>
            <div style="padding-left:20px;">
                <ul class="notice_page_ul">
                    <li>Fields marked in <font color="red" size="3">*</font> are mandatory</li>
                    <li>Please validate the data before submit & It will be subjected to verify</li>
                </ul>
            </div>
        </div>
        <form action="joinerFormAddOrUpdateStepTwo.htm" method="post"  name="joinerFormOne" id="idealJavaForm" class="idealJavaForm" enctype="multipart/form-data" onsubmit="return attachmentValidation();">
            <div id="commonforms">
                <div class="commonformheader" style="padding-left:10px;">General Information</div>
                <div>
                    <div id="page1" class="visible">
                        <table width="100%" cellspacing="5">
                            <tr>
                                <td width="33%">
                                    <label for="firstName" class="requiredLabel">First Name<font color="red" size="3px;">*</font></label>
                                    <input name="firstName" id="firstName" type="text" class="required alphaWithDot" value="${employeeData.firstName}" />
                                    <input type="hidden" name="jfId" value ="${employeeData.jfId}" id="jfId"/>
                                    <input type="hidden" name="trackNumber" value ="${trackNumber}" id="trackNumber"/>
                                </td>
                                <td width="33%">
                                    <label for="middleName" class="requiredLabel">Middle Name</label>
                                    <input name="middleName" id="middleName" type="text" class="" value="${employeeData.middleName}"  />
                                </td>
                                <td width="33%">
                                    <label for="lastname" class="requiredLabel">Last Name<font color="red" size="3px;">*</font></label>
                                    <input name="lastName" id="lastname" type="text" class="required alphaWithDot" value="${employeeData.lastName}"  />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="gender" class="requiredLabel">Gender<font color="red" size="3px;">*</font></label>
                                    <input name="gender" type="radio" value="m" <c:if test="${joinerData.gender == 'm' || empty joinerData.gender }">checked</c:if> id="male" style="width:18px;" /><label for="male" class="labelSmall">Male</label>
                                    <input name="gender" type="radio" value="f" <c:if test="${joinerData.gender == 'f'}">checked</c:if> id="female" style="width:18px;" /><label for="female" class="labelSmall">Female</label>
                                        <br><span id="errorMsg" style="display:none;color: red;padding-left: inherit;"  >Required Data Missing</span>
                                    </td>
                                    <td>
                                        <label for="fatherName" class="requiredLabel">Father Name<font color="red" size="3px;">*</font></label>
                                        <input id="fatherName" name="fatherName" class="required alphaWithDot"  type="text" value="${joinerData.fatherName}" />
                                </td>
                                <td>
                                    <label for="dateOfBirth" class="requiredLabel">Date of Birth<font color="red" size="3px;">*</font></label>
                                    <input type="text" name="dateOfBirth" id="dateOfBirth" readonly class="required" value="${employeeData.dateOfBirth}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="maritalStatus" class="requiredLabel">Marital Status<font color="red" size="3px;">*</font></label>
                                    <select name="maritalStatus" id="maritalStatus" class="selectbox1 required" onchange="getMaritalStausOthers(this.value);">
                                        <option  value="">-- Select Marital Status --</option>
                                        <c:forEach items="${maritalStatus}" var="marStatusValue" varStatus="marStatus">
                                            <c:choose>
                                                <c:when test="${joinerData.maritalStatus==marStatusValue.maritalstatusId}">
                                                    <option value="${marStatusValue.maritalstatusId}" selected="selected">${marStatusValue.maritalstatusName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${marStatusValue.maritalstatusId}">${marStatusValue.maritalstatusName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </td>
                                <c:choose>
                                    <c:when test="${joinerData.maritalStatus!='m'}">
                                        <c:set value="disabled" var="marriedStatus" />
                                    </c:when>
                                    <c:otherwise>
                                        <c:set value="" var="marriedStatus" />
                                    </c:otherwise>
                                </c:choose>
                                <td>
                                    <label for="dateOfMarriage" class="requiredLabel" >Date of Marriage</label>
                                    <input type="text" name="dateOfMarriage" id="dateOfMarriage" ${marriedStatus}  readonly value="${(joinerData.dateOfMarriage) !='00-00-0000' ?joinerData.dateOfMarriage:''}"/>
                                </td>
                                <td>
                                    <label for="spouseName" class="requiredLabel" >Spouse Name</label>
                                    <input name="spouseName" id="spouseName" type="text" ${marriedStatus} value="${joinerData.spouseName}" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="placeOfBirth" class="requiredLabel">Place of Birth<font color="red" size="3px;">*</font></label>
                                    <input name="placeOfBirth" type="text" class="required" id ="placeOfBirth" value="${joinerData.placeOfBirth}" />
                                </td>
                                <td>
                                    <label for="religion" class="requiredLabel">Religion</label>
                                    <input name="religion" class="alpha" id="religion" type="text"  value="${joinerData.religion}"/>
                                </td>
                                <td>
                                    <label for="motherTongue" class="requiredLabel">Mother Tongue</label>
                                    <input name="motherTongue" id="motherTongue" class="" type="text" value="${joinerData.motherTongue}"/>
                                </td>
                            </tr>
                            <tr>
                                <td valign="top">
                                    <label for="nationality" class="requiredLabel">Nationality<font color="red" size="3px;">*</font></label>
                                    <select name="nationality" id="nationality" class="required" >
                                        <option value="" >--Select Country--</option>
                                        <c:forEach items="${nationalityList}" var="item">
                                            <c:set var="selval" value="" ></c:set>
                                            <c:if test="${item.nationalityid == joinerData.nationality}">
                                                <c:set var="selval" value="selected=selected" ></c:set>
                                            </c:if>
                                            <option ${selval} value="${item.nationalityid}" >${item.nationalityname}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <label for="presentAddress" class="requiredLabel">Present Address<font color="red" size="3px;">*</font></label>
                                    <textarea rows="3" cols="4" id="presentAddress" class="required" name="presentAddress">${joinerData.presentAddress}</textarea>
                                </td>
                                <td>
                                    <label for="permanentAddress" class="requiredLabel">Permanent Address(<span style="font-size: smaller;">Should not exceed more than 60 characters</span>)<font color="red" size="3px;">*</font></label>
                                    <textarea name="permanentAddress" id="permanentAddress" class="required" rows="3" cols="4" maxlength="60" >${joinerData.permanentAddress}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for = "countryPermanent" class="requiredLabel">Country<br>(Permanent)<font color="red" size="3px;">*</font></label>
                                    <select id="country_id_1" name="country" class = "required" onChange="loadState1(this.value, 'region', 1)">
                                        <option value="">-- Country --</option>
                                        <c:forEach items="${countryList}" var="res">
                                            <c:set var="selval" value=""></c:set>
                                            <c:if test="${res.countryId == joinerAddressData.country}">
                                                <c:set var="selval" value="selected=selected" ></c:set>
                                            </c:if>
                                            <option ${selval} value="${res.countryId}">${res.countryName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <label for = "regionPermanent" class="requiredLabel">Region<br>(Permanent)<font color="red" size="3px;">*</font></label>
                                    <input type="hidden" value="${joinerAddressData.region}" id="selected_region_id_1" />
                                    <select id="region_id_1" name="region" class = "required" onChange="loadState1(this.value, 'city', 1)">
                                        <option value="">-- Region --</option>
                                    </select>
                                </td>
                                <td>
                                    <label for = "cityPermanent" class="requiredLabel">City<br>(Permanent)<font color="red" size="3px;">*</font></label>
                                    <input type="hidden" value="${joinerAddressData.city}" id="selected_city_id_1" />
                                    <select id="city_id_1"  class = "required" name="city" >
                                        <option value="">-- City --</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for = "zipCodePermanent" class="requiredLabel">Zip code<br>(Permanent)<font color="red" size="3px;">*</font></label>
                                    <input type="text" id="zip_code_1" name="zip_code" value="${joinerAddressData.zip_code}" class = "required" style="width:50%" maxlength="6" />
                                </td> 
                                <td>
                                    <label for="phoneNumberPermanent" class="requiredLabel">Phone Number<br>(Permanent)</label>
                                    <input name="phoneNumberPermanent" class="number" id="phoneNumberPermanent" type="text" maxlength="20" value="${joinerData.phoneNumberPermanent}" />
                                </td>
                                <td valign="top">
                                    <label for="phoneNumberPresent" class="requiredLabel">Mobile Number<br>(Present)</label>
                                    <input name="phoneNumberPresent" class="number" type="text" maxlength="20" value="${joinerData.phoneNumberPresent}" id="phoneNumberPresent" />
                                </td>

                            </tr>
                            <tr>
                                <td>
                                    <label for="panNumber" class="requiredLabel" >PAN Number<font color="red" size="3px;">*</font></label>
                                    <input name="panNo" id="panNo" style="text-transform: uppercase" class="alphanumeric required" type="text" value="${employeeData.panNo}" maxlength="20" />
                                    <div class="customError" id="panNameRequired" style="display:none">Enter Valid PAN number</div>
                                </td>
								<td>
                                    <label for="pfNumber" class="requiredLabel" >PF Number<font color="red" size="3px;">*</font></label>
                                    <input name="previousPfNumber" class = "required"  type="text" value="${joinerData.previousPfNumber}" maxlength="20" />
                                </td>
                                <td>
                                    <label for="uanNumber" class="requiredLabel" >UAN Number<font color="red" size="3px;">*</font></label>
                                    <input name="uanNo" class="alphanumeric required" type="text" value="${joinerData.uanNo}" maxlength="20" />
                                </td>
                            </tr> 
                            
                            
                               <tr>     
                                   <td>
                                    <label for="empPhotoUpload" class="requiredLabel">UAN Proof</label>
                                        <c:choose>
                                            <c:when test="${joinerData.empUanPfFileName!=null}">
                                            <a href="fileDownload.htm?fileName=${joinerData.empUanPfFileName}&fileType=${joinerData.empUanPfFileType}">${fn:split(joinerData.empUanPfFileName,'~~')[2]}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <input name="empUanProof" id="empUanProof" type="file"  <%--value=""--%>  style="width:140px;" />
                                            <div id="empAddProof_err" style="color:red;display: none;">.zip, .rar, .jar & .war files are not accepted.</div>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
								

                                <td>
                                    <label for="empPhotoUpload" class="requiredLabel" >Photo Upload<font color="red" size="3px;">*</font></label>
                                    <c:choose>
                                        <c:when test="${joinerData.empFileName!=null}">
                                            <a id="empPhotoAvailable" href="fileDownload.htm?fileName=${joinerData.empFileName}&fileType=${joinerData.empFileType}">${fn:split(joinerData.empFileName,'~~')[2]}</a>
                                            <input name="empPhotoUpload" id="empPhotoUpload" type="file"  <%--value="" size="9"--%> style="width:140px;"/>
                                            <div id="empPhoto_err" style="color:red;display: none;">.zip, .rar, .jar & .war files are not accepted.</div>
                                        </c:when>
                                        <c:otherwise>
                                            <input name="empPhotoUpload" class="required" id="empPhotoUpload" type="file"  <%--value="" size="9"--%> style="width:140px;"/>
                                            <div id="empPhoto_err" style="color:red;display: none;">.zip, .rar, .jar & .war files are not accepted.</div>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <label for="empPhotoUpload" class="requiredLabel">Address Proof (Govt. Provided)<font color="red" size="3px;">*</font></label>
                                        <c:choose>
                                            <c:when test="${joinerData.empAddPfFileName!=null}">
                                            <a href="fileDownload.htm?fileName=${joinerData.empAddPfFileName}&fileType=${joinerData.empAddPfFileType}">${fn:split(joinerData.empAddPfFileName,'~~')[2]}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <input name="empAddProof" class="required" id="empAddProof" type="file"  <%--value=""--%>  style="width:140px;" />
                                            <div id="empAddProof_err" style="color:red;display: none;">.zip, .rar, .jar & .war files are not accepted.</div>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td colspan="3"><i>(Upload white background passport size photo in JPEG format )</i></td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="adharNumber" class="requiredLabel" >Aadhaar Number<font color="red" size="3px;">*</font></label>
                                    <input name="adharNo" id="adharNo" class="required" type="text" value="${joinerData.adharNo}" maxlength="12" />
                                    <div class="customError" id="adharNameRequired" style="display:none">Enter Valid Adhar number</div>
                                </td>
                                <td>
                                    <label for="empPhotoUpload" class="requiredLabel">Aadhaar Proof (Govt. Provided)<font color="red" size="3px;">*</font></label>
                                        <c:choose>
                                            <c:when test="${joinerData.empAdharFileName!=null}">
                                            <a href="fileDownload.htm?fileName=${joinerData.empAdharFileName}&fileType=${joinerData.empAdharFileType}">${fn:split(joinerData.empAdharFileName,'~~')[2]}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <input name="empAdharProof" class="required" id="empAdharProof" type="file"  <%--value=""--%>  style="width:140px;" />
                                            <div id="empAdharProof_err" style="color:red;display: none;">.zip, .rar, .jar & .war files are not accepted.</div>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <label for="bloodGroup" class="requiredLabel">Blood Group<font color="red" size="3px;">*</font></label>
                                    <select name="bloodGroup" id="bloodGroup" class="required" ${disableStatus}>
                                        <option value="">--Select Blood Group--</option>
                                        <c:forEach items="${bloodGroupList}" var="bloodGroupValues" varStatus="index">
                                            <c:choose>
                                                <c:when test="${fn:toUpperCase(joinerData.bloodGroup)==bloodGroupValues.value}">
                                                    <option title="${bloodGroupValues.value}" value="${bloodGroupValues.key}" selected>${bloodGroupValues.value}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option title="${bloodGroupValues.value}" value="${bloodGroupValues.key}">${bloodGroupValues.value}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="empPhotoUpload" class="requiredLabel">Signature<font color="red" size="3px;">*</font></label>
                                    <c:choose>
                                        <c:when test="${joinerData.empSignatureFileName!=null}">
                                            <a href="fileDownload.htm?fileName=${joinerData.empSignatureFileName}&fileType=${joinerData.empSignatureFileType}">${fn:split(joinerData.empSignatureFileName,'~~')[2]}</a>
                                            <input name="empSignatureFile" id="empSignatureFile" type="file"  <%--value=""--%>  style="width:140px;" />
                                            <div id="empSignature_err" style="color:red;display: none;">.zip, .rar, .jar & .war files are not accepted.</div>
                                        </c:when>
                                        <c:otherwise>
                                            <input name="empSignatureFile" class="required" id="empSignatureFile" type="file"  <%--value=""--%>  style="width:140px;" />
                                            <div id="empSignature_err" style="color:red;display: none;">.zip, .rar, .jar & .war files are not accepted.</div>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <label for="bankAccType" class="requiredLabel">Bank Account<font color="red" size="3px;">*</font></label>
                                    <select name="bankAccType" id="bankAccType" class="selectbox1 required" onchange="return hideBankDetails(this.value, 1);">
                                        <option  value="">-- Select Account Type --</option>
                                        <c:forEach items="${bankAccType}" var="bankAccTypeValue" varStatus="bankAccTypeIndex">
                                            <c:choose>
                                                <c:when test="${joinerData.bankAccType==bankAccTypeIndex.index}">
                                                    <option value="${bankAccTypeIndex.index}" selected="selected">${bankAccTypeValue}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${bankAccTypeIndex.index}">${bankAccTypeValue}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div id="bankDetailsTd" style="display:none;">
                    <div class="commonformheader" style="">Bank Details</div>
                    <table width="100%" border="0" cellspacing="5">
                        <tr>
                            <td width="33%">
                                <label for="nameInBankRecords" class="requiredLabel">Name<font color="red" size="3px;">*</font></label>
                                <input name="nameInBankRecords" class="alphaWithDot" id="nameInBankRecords" type="text" value="${joinerData.nameInBankRecords}" />
                                <div class="customError" id="nameInBankRequired" style="display:none">Enter Name In Bank Records</div>
                            </td>
                            <td width="33%">
                                <label for="bankName" class="requiredLabel">Bank Name<font color="red" size="3px;">*</font></label>
                                <select name="bankName" id="bankName" class="selectbox1">
                                    <option value="">--Select--</option>
                                    <c:forEach items="${bankNames}" var="bankNamesValue" varStatus="bankNamesIndex">
                                        <c:choose>
                                            <c:when test="${joinerData.bankName==bankNamesIndex.index}">
                                                <option value="${bankNamesIndex.index}" selected="selected">${bankNamesValue}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${bankNamesIndex.index}">${bankNamesValue}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                                <div class="customError" id="bankNameRequired" style="display:none">Enter Bank Name</div>
                            </td>
                            <td width="33%">
                                <label for="accountNumber" class="requiredLabel">Account No.<font color="red" size="3px;">*</font></label>
                                <input name="accountNumber" class="number" maxlength="20" type="text" id="accountNumber" value="${joinerData.accountNumber}" />
                                <div class="customError" id="accountNumberRequired" style="display:none">Enter Account Number</div>
                            </td>
                        </tr>
                        <tr>
                            <td><i>(As per Bank Records)</i></td><td></td><td></td>
                        </tr>
                        <tr>
                            <td>
                                <label for="accountNumber" class="requiredLabel">Confirm Acc. No.<font color="red" size="3px;">*</font></label>
                                <input name="confirmAccountNumber" class="number" maxlength="20" type="password" id="confirmAccountNumber" value="${joinerData.accountNumber}"  <%--onpaste="return false"--%> onblur="return checkAccountNumber(this.value, document.getElementById('accountNumber').value)" />
                                <div class="customError" id="confirmAccountNumberRequired" style="display:none">Enter Confirm Account Number</div>
                            </td>
                            <td>
                                <label for="branch" class="requiredLabel">Branch<font color="red" size="3px;">*</font></label>
                                <input name="branch" class="alphaWithDot" id="branch" type="text" value="${joinerData.branch}" />
                                <div class="customError" id="branchRequired" style="display:none">Enter Branch Name</div>
                            </td>
                            <td>
                                <label for="branch" class="requiredLabel">Country<font color="red" size="3px;">*</font></label>
                                <select name="bankCountry" id="bankCountry" style="width:150px;" onChange="loadState(this.value, 'region');">
                                    <option value="" >--Select Country--</option>
                                    <c:forEach items="${countryList}" var="item">
                                        <c:set var="selval" value="" ></c:set>
                                        <c:if test="${item.countryId == regionData.bankCountry}">
                                            <c:set var="selval" value="selected=selected" ></c:set>
                                        </c:if>
                                        <option ${selval} value="${item.countryId}" >${item.countryName}</option>
                                    </c:forEach>
                                </select>
                                <div class="customError" id="bankCountryRequired" style="display:none">Select Country Name</div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="branch" class="requiredLabel">Region<font color="red" size="3px;">*</font></label>
                                <select name="bankRegion" id="bankRegion" style="width:150px;" onChange="loadState(this.value, 'city');">
                                    <option value="" >--Select Region--</option>
                                    <c:if test="${joinerData.bankCity != null && joinerData.bankCity != '' }">
                                        <c:forEach items="${regionList}" var="item">
                                            <c:set var="selval" value="" ></c:set>
                                            <c:if test="${item.regionId == regionData.bankRegion}">
                                                <c:set var="selval" value="selected" ></c:set>
                                            </c:if>
                                            <option ${selval} value="${item.regionId}" >${item.regionName}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                                <div class="customError" id="bankRegionRequired" style="display:none">Select Region Name</div>
                            </td>
                            <td>
                                <label for="branch" class="requiredLabel">City<font color="red" size="3px;">*</font></label>
                                <select name="bankCity" id="bankCity" style="width:150px;" >
                                    <option value="" >--Select City--</option>
                                    <c:if test="${joinerData.bankCity != null && joinerData.bankCity != '' }">
                                        <c:forEach items="${cityList}" var="item">
                                            <c:set var="selval" value="" ></c:set>
                                            <c:if test="${item.regionId == joinerData.bankCity}">
                                                <c:set var="selval" value="selected=selected" ></c:set>
                                            </c:if>
                                            <option ${selval} value="${item.regionId}" >${item.regionName}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                                <div class="customError" id="bankCityRequired" style="display:none">Select City Name</div>
                            </td>
                            <td>
                                <div id="bankProofYes" style="display: block;float: none;" class="">
                                    <label for="branch" class="">Bank Proof</label>
                                    <c:choose>
                                        <c:when test="${joinerData.bankFileName!=null}">
                                            <c:if test="${joinerData.status == 1 || joinerData.status == 4 || joinerData.status == 2}">
                                            <input type="file" name="canChequeLeafYes" id="canChequeLeaf" <%--value="" style="size:9px"--%> />
                                            </c:if>
                                            <a href="fileDownload.htm?fileName=${joinerData.bankFileName}&fileType=${joinerData.bankFileType}">${fn:split(joinerData.bankFileName,'~~')[2]}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="file" name="canChequeLeafYes" id="canChequeLeaf" <%--value="" style="size:9px"--%> />
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div id="bankProofNo" style="display:none;">
                                    <label for="branch" class="requiredLabel" >Bank Proof</label>
                                    <input type="file" name="canChequeLeaf" id="canChequeLeaf" <%--value="" style="size:9"--%> />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td></td><td></td><td><i>(Cancelled Cheque Leaf)</i></td>
                        </tr>
                    </table>
                </div>
                <div>
                    <table cellspacing="5" border="0" width="100%">
                        <tr>
                            <td colspan="3" align="center">
                                <c:if test="${printStatus!='yes'}">
                                    <input type="submit" id="continuebutton0" class="continuebutton"  value="Save &amp; Continue" onclick="return bankValidation();" >
                                </c:if>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </form>
    </div>
    <script type="text/javascript">
    setDOM();
    function checkAccountNumber(confirmAcntNumber, acntNumber) {
        if (confirmAcntNumber != acntNumber) {
            alert("Confirm Account Number doesnt match with account number");
            document.getElementById('confirmAccountNumber').focus();
            return false;
        }
        return true;
    }

    $(document).ready(function() {

        loadState1($("#country_id_1").val(), 'region', 1);
        loadState1($("#selected_region_id_1").val(), 'city', 1);

    });

    </script>
