<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<html>
    <head>
       
    </head>
    <script>
        function downloadFileUser(filePath,originalName){
           
            $('#kycDetailsForm').attr("action","downloadFile_user.htm?filePath="+filePath+"&originalName="+originalName);
            document.kycDetailsForm.submit();
            //startLoading();
        }
    </script>
    <%@include file="/WEB-INF/jsp/com/defiance/employees/common.jsp" %>
    <div class="contentwrap" style="height:auto;">
        <%@include file="/WEB-INF/jsp/menu.jsp" %>
        <div id="commonforms">


            <div align="center">
                <span  id="errormessage" class="errormessage" style="padding-left:20px;" ></span>
                <div class="commonformheader" style="padding-left:15px;"><span  id="errormessage" class="errormessage" style="padding-left:20px;" ></span></div>
                <form name="kycDetailsForm" id="kycDetailsForm" action="kycDetails.htm" method="post" enctype="multipart/form-data">
                    <input type="hidden" id="actionName" name="actionName" value="${actionName}" />
                    <c:choose>
                        <c:when test="${accessType == 'HR'}">
                            <table class="tableStructure" border="0">
                                <tr class="headerCenter">
                                    <th width="10%">Employee Name</th>
                                    <th width="10%">Highest Qualification</th>
                                    <th width="10%">Aadhar Number</th>
                                     <th width="10%">Aadhar File</th>
                                    <th width="10%">Pan No</th>
                                    <th width="10%">Driving Licence</th>
                                    <th width="10%">Voter Id</th>
                                    <th width="10%">Ration Card Id</th>
                                </tr>
                                <c:forEach items="${result}" var="data" varStatus="dataStatus">
                                    <tr class="${dataStatus.count%2==0?'altrow':''}">
                                        <td valign ="top" align="left">
                                            ${data.empName}
                                        </td>
                                        <td valign ="top" align="left">
                                            ${data.highestQualification}
                                        </td>
                                        <td valign ="top" align="left">
                                            ${data.adhar}
                                        </td>
                                         <td valign ="top" align="left">
                                            <c:choose>
                                            <c:when  test="${empty data.filename}">
                                                 None
                                            </c:when>
                                             <c:otherwise>
                                               <a  href ="javascript:;" onclick="downloadFileUser('${data.fullFileName}','${data.filename}');">${data.filename}</a> 
                                                <input type="hidden" readonly id="fullFileName" name="fullFileName" value="${data.filename}"/>     
                                           </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td valign ="top" align="left">
                                            ${data.panNo}
                                        </td>
                                        <td valign ="top" align="left">
                                            ${data.drivingLicence}
                                        </td>
                                        <td valign ="top" align="left">
                                            ${data.voterId}
                                        </td>
                                        <td valign ="top" align="left">
                                            ${data.rationId}
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty(result)}">
                                    <tr>
                                        <td colspan="12" align="center">
                                            <font color="red">No Records Found.Search it by Employee Name / Employee Id to find records</font>
                                        </td>
                                    </tr>
                                </c:if>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${!empty(result)}">
                            <c:forEach items="${result}" var="result">
                                <table width="99%" border="0" align="center" cellspacing="5" class="EmpInfo">
                                    <tr>
                                        <td style="width:33%">
                                            <label for="empNumber" class="">Employee Number</label>
                                            <input type="text" name="empNumber" value ="${result.empNumber}" id="empNumber"  disabled />
                                        </td>
                                        <td style="width:33%">
                                            <label for="empName">Employee Name</label>
                                            <input type="text" name="empName" value ="${result.empName}" id="empName"  disabled />
                                        </td>
                                        <td style="width:33%">
                                            <label for="email" class="">Email<font color="red" size="4px;">*</font></label>
                                            <input type="text" name="email" value ="${result.email}" id="email"  />
                                        </td>
                                    </tr>
                                    <tr>

                                        <td style="width:33%">
                                            <label for="maritalStatus">Marital Status<font color="red" size="4px;">*</font></label>
                                            <input type="text" name="maritalStatus" value ="${result.maritalStatus}" id="maritalStatus" disabled />
                                        </td>
                                        <td id="specially_able_td" style="width:33%">
                                            <span><label for="speciallyAble" class="">Specially Able<font color="red" size="4px;">*</font></label> 
                                                <input type="hidden" name="speciallyAble" value ="${result.speciallyAble}" id="speciallyAble"  disabled/></span>
                                            <span><input type="radio" name="speciallyAble" id="speciallyAbleYes" value="Yes" style="float:none !important;"   onClick="showCategory('y')"/>Yes
                                                <input type="radio" name="speciallyAble" id="speciallyAbleNo" value="No" style="float:none !important;" onClick="showCategory('n')"/>No</span>

                                        </td>
                                        <td style="width:33%" id="speciallyAbleCategoryTd">
                                            <label for="speciallyAbleCategory" class="">Specially Able Category<font color="red" size="4px;">*</font></label>
                                            <input type="text" name="speciallyAbleCategory" value ="${result.speciallyAbleCategory}" id="speciallyAbleCategory"  />
                                            <div  id="speciallyAbleCategoryDiv" class="errormessage" style="padding-left:20px;" ></div>
                                        </td>
                                   
                                        <td style="width:33%">
                                            <label for="highestQualification" class="">Highest Qualification<font color="red" size="4px;">*</font></label>
                                            <input type="text" name="highestQualification" value ="${result.highestQualification}" id="highestQualification"  />

                                        </td>
                                        </tr>
                                          <tr>
                                       <td style="width:20%">
                                            <label for="adharUpload" class="">Upload Aadhar</label>
                                            <c:choose>
                                            <c:when  test="${empty result.filename}">
                                                 <input type="file" name="file"  id="file"  style="width: 176px"/>
                                                  <span  id="fileDiv" class="errormessage" style="padding-left:20px;" ></span>
                                            </c:when>
                                             <c:otherwise>
                                               <a  href ="javascript:;" onclick="downloadFileUser('${result.fullFileName}','${result.filename}');">${result.filename}</a> 
                                                <input type="hidden" readonly id="fullFileName" name="fullFileName" value="${result.filename}"/>     
                                           </c:otherwise>
                                            </c:choose>
                                        </td>
                                  
                                        <td style="width:33%">
                                            <label for="adhar" class="">Aadhar<font color="red" size="4px;">*</font></label>
                                            <input type="text" name="adhar" value ="${result.adhar}" id="adhar"  maxlength="12"/>
                                              <span  id="adharDiv" class="errormessage" style="padding-left:20px;" ></span>
                                        </td>
                                        <td style="width:33%">
                                            <label for="nameAsAdhar" class="">Name As Aadhar<font color="red" size="4px;">*</font></label>
                                            <input type="text" name="nameAsAdhar" value ="${result.nameAsAdhar}" id="nameAsAdhar"  />
                                            <span  id="nameAsAdharDiv" class="errormessage" style="padding-left:20px;" ></span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:33%">
                                            <label for="panNo" class="">Pan No</label>
                                            <input type="text" name="panNo" value ="${result.panNo}" id="panNo"  style="text-transform: uppercase"/>
                                            <span  id="panDiv" class="errormessage" style="padding-left:20px;" ></span>
                                        </td>
                                        <td style="width:33%">
                                            <label for="nameAsPan">Name As Pan<font color="red" size="4px;">*</font></label>
                                            <input type="text" name="nameAsPan" value ="${result.nameAsPan}" id="nameAsPan" />
                                            <span  id="nameAsPanDiv" class="errormessage" style="padding-left:20px;" ></span>
                                        </td>
                                        <td style="width:33%">
                                            <label for="drivingLicence" class="">Driving Licence</label>
                                            <input type="text" name="drivingLicence" value ="${result.drivingLicence}" id="drivingLicence"  />
                                        </td>
                                    </tr>
                                    <tr>

                                        <td style="width:33%">
                                            <label for="nameAsdrivingLicence" class="">Name As Driving Licence</label>
                                            <input type="text" name="nameAsdrivingLicence" value ="${result.nameAsdrivingLicence}" id="nameAsdrivingLicence"  style="width: 156px";/>
                                            <span  id="nameAsdrivingLicenceDiv" class="errormessage" style="padding-left:20px;" ></span>
                                        </td>
                                        <td style="width:33%">
                                            <label for="Expiry Date">Expiry Date</label>
                                            <input type="text" name="drivingLicenceExpiryDate" value ="${result.drivingLicenceExpiryDate}" id="drivingLicenceExpiryDate"  />
                                            <span  id="drivingLicenceExpiryDateDiv" class="errormessage" style="padding-left:20px;" ></span>
                                        </td>
                                        <td style="width:33%">
                                            <label for="voterId">Voter Id</label>
                                            <input type="text" name="voterId" value ="${result.voterId}" id="voterId"  />
                                        </td>
                                    </tr>
                                    <tr>


                                        <td style="width:33%">
                                            <label for="nameAsVoterId" class="">Name AS Voter Id</label>
                                            <input type="text" name="nameAsVoterId" value ="${result.nameAsVoterId}" id="nameAsVoterId"  />
                                            <span  id="nameAsVoterIdDiv" class="errormessage" style="padding-left:20px;" ></span>
                                        </td>


                                        <td style="width:33%">
                                            <label for="rationId">Ration Card Id</label>
                                            <input type="text" name="rationId" value ="${result.rationId}" id="rationId"  />
                                        </td>
                                        <td style="width:33%">
                                            <label for="nameAsRationId" class="">Name AS Ration Card</label>
                                            <input type="text" name="nameAsRation" value ="${result.nameAsRation}" id="nameAsRation"  />
                                            <span  id="nameAsRationDiv" class="errormessage" style="padding-left:20px;" ></span>
                                        </td>
                                        <td style="width:33%"></td>
                                    </tr>
                                </c:forEach>
                                    </c:if>
                                <c:if test="${empty(result)}">
                                    <table width="99%" border="0" align="center" cellspacing="5" class="EmpInfo">
                                    <tr>
                                        <td style="width:33%">
                                            <label for="empNumber" class="">Employee Number</label>
                                            <input type="text" name="empNumber"  id="empNumber"  disabled />
                                        </td>
                                        <td style="width:33%">
                                            <label for="empName">Employee Name</label>
                                            <input type="text" name="empName"  id="empName"  disabled />
                                        </td>
                                        <td style="width:33%">
                                            <label for="email" class="">Email<font color="red" size="4px;">*</font></label>
                                            <input type="text" name="email"  id="email"  />
                                        </td>
                                    </tr>
                                    <tr>

                                        <td style="width:33%">
                                            <label for="maritalStatus">Marital Status<font color="red" size="4px;">*</font></label>
                                            <input type="text" name="maritalStatus"  id="maritalStatus" disabled />
                                        </td>
                                        <td id="specially_able_td" style="width:33%">
                                            <span><label for="speciallyAble" class="">Specially Able<font color="red" size="4px;">*</font></label> 
                                                <input type="hidden" name="speciallyAble"  id="speciallyAble"  disabled/></span>
                                            <span><input type="radio" name="speciallyAble" id="speciallyAbleYes" value="Yes" style="float:none !important;"   onClick="showCategory('y')"/>Yes
                                                <input type="radio" name="speciallyAble" id="speciallyAbleNo" value="No" style="float:none !important;" onClick="showCategory('n')"/>No</span>

                                        </td>
                                        <td style="width:33%" id="speciallyAbleCategoryTd">
                                            <label for="speciallyAbleCategory" class="">Specially Able Category<font color="red" size="4px;">*</font></label>
                                            <input type="text" name="speciallyAbleCategory"  id="speciallyAbleCategory"  />
                                            <div  id="speciallyAbleCategoryDiv" class="errormessage" style="padding-left:20px;" ></div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td style="width:33%">
                                            <label for="highestQualification" class="">Highest Qualification<font color="red" size="4px;">*</font></label>
                                            <input type="text" name="highestQualification"  id="highestQualification"  />

                                        </td>

                                        <td style="width:33%">
                                            <label for="adhar">Aadhar</label>
                                            <input type="text" name="adhar"  id="adhar"  />
                                            <span  id="adharDiv" class="errormessage" style="padding-left:20px;" ></span>
                                        </td>
                                        <td style="width:33%">
                                            <label for="nameAsAdhar" class="">Name As Aadhar</label>
                                            <input type="text" name="nameAsAdhar"  id="nameAsAdhar"  />
                                            <span  id="nameAsAdharDiv" class="errormessage" style="padding-left:20px;" ></span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width:33%">
                                            <label for="panNo" class="">Pan No</label>
                                            <input type="text" name="panNo"  id="panNo"  style="text-transform: uppercase"/>
                                             <span  id="panDiv" class="errormessage" style="padding-left:20px;" ></span>
                                        </td>
                                        <td style="width:33%">
                                            <label for="nameAsPan">Name As Pan</label>
                                            <input type="text" name="nameAsPan"  id="nameAsPan" />
                                            <span  id="nameAsPanDiv" class="errormessage" style="padding-left:20px;" ></span>
                                        </td>
                                        <td style="width:33%">
                                            <label for="drivingLicence" class="">Driving Licence</label>
                                            <input type="text" name="drivingLicence"  id="drivingLicence"  />
                                        </td>
                                    </tr>
                                    <tr>

                                        <td style="width:33%">
                                            <label for="nameAsdrivingLicence" class="">Name As Driving Licence</label>
                                            <input type="text" name="nameAsdrivingLicence"  id="nameAsdrivingLicence"  />
                                            <span  id="nameAsdrivingLicenceDiv" class="errormessage" style="padding-left:20px;" ></span>
                                        </td>
                                        <td style="width:33%">
                                            <label for="Expiry Date">Expiry Date</label>
                                            <input type="text" name="drivingLicenceExpiryDate"  id="drivingLicenceExpiryDate"  />
                                            <span  id="drivingLicenceExpiryDateDiv" class="errormessage" style="padding-left:20px;" ></span>
                                        </td>
                                        <td style="width:33%">
                                            <label for="voterId">Voter Id</label>
                                            <input type="text" name="voterId"  id="voterId"  />
                                        </td>
                                    </tr>
                                    <tr>


                                        <td style="width:33%">
                                            <label for="nameAsVoterId" class="">Name AS Voter Id</label>
                                            <input type="text" name="nameAsVoterId"  id="nameAsVoterId"  />
                                            <span  id="nameAsVoterIdDiv" class="errormessage" style="padding-left:20px;" ></span>
                                        </td>


                                        <td style="width:33%">
                                            <label for="rationId">Ration Card Id</label>
                                            <input type="text" name="rationId"  id="rationId"  />
                                        </td>
                                        <td style="width:33%">
                                            <label for="nameAsRationId" class="">Name AS Ration Card</label>
                                            <input type="text" name="nameAsRation"  id="nameAsRation"  />
                                            <span  id="nameAsRationDiv" class="errormessage" style="padding-left:20px;" ></span>
                                        </td>
                                        <td style="width:33%"></td>
                                    </tr>
                                </c:if>
                            </c:otherwise>
                        </c:choose>

                        <c:if test="${accessType != 'HR'}" >
                            <c:if test="${buttonStatus=='editMode' || buttonStatus==null}">
                                <tr>
                                    <td>&nbsp;</td>
                                    <td align="center">
                                         <input type="submit" class="submitbutton" name="buttonName" value="submit">  

                                    </td>
                                    <td>&nbsp;</td>
                                </tr>
                            </c:if>
                        </c:if>

                    </table>
                </form>
                <div align="left">
                    <c:if test="${accessType == 'HR' && paging[0] > 1}">
                        <%@include file="/WEB-INF/jsp/com/defiance/employees/paging.jsp" %>
                    </c:if>
                </div>
            </div>
        </div>
    </div>          
</body>

</html>
<style>
    .errormessage{
        color: red;
    }
    #specially_able_td span{
        padding-right: 58px;
    }
</style>
<script>
                                                $(function() {
                                                    $("#drivingLicenceExpiryDate").datepicker({
                                                        changeMonth: true,
                                                        changeYear: true,
                                                        showButtonPanel: true,
                                                        dateFormat: 'dd-mm-yy'
                                                    });

                                                });





                                                $(document).ready(function() {
                                                    $('#speciallyAbleCategoryTd').hide();
                                                    if ($('#speciallyAble').val() == 'Yes') {
                                                        $('#speciallyAbleNo').removeAttr("checked");
                                                        $('#speciallyAbleYes').attr("checked", "checked");
                                                        $('#speciallyAbleCategoryTd').show();
                                                    } else if ($('#speciallyAble').val() == 'No') {
                                                        $('#speciallyAbleYes').removeAttr("checked");
                                                        $('#speciallyAbleNo').attr("checked", "checked");
                                                    } else {
                                                        $('#speciallyAbleNo').attr("checked", "checked");
                                                    }
                                                });
                                                function showCategory(value) {
                                                    if (value == 'y') {
                                                        $('#speciallyAbleCategoryTd').show();
                                                    }
                                                    else {
                                                        $('#speciallyAbleCategoryTd').hide();
                                                        $('#speciallyAbleCategory').val('');
                                                    }
                                                }

                                                $(function() {


                                                    $("#kycDetailsForm").validate({
                                                        rules: {
                                                            maritalStatus: "required",
                                                            highestQualification: "required",
                                                            adhar:"required",
                                                            email: {
                                                                required: true,
                                                                email: true

                                                            }
                                                        },
                                                        panNo:"required",
                                                        messages: {
                                                            maritalStatus: "Please enter your marital status",
                                                            highestQualification: "Please enter your highest Qualification",
                                                            adhar:"Please enter your Aadhar number correctly",
                                                            email: "Please enter valid personal email",
                                                            panNo:"Please enter PAN number correctly"
                                                            

                                                        },
                                                        submitHandler: function(form) {

                                                            if (validateKycDetailsForm()) {

                                                                form.submit();
                                                            }
                                                        }
                                                    });

                                                });



                                                function validateKycDetailsForm() {
                                                 
                                                    var errorFlag = 0;
                                                    var atLeastCountOne = 0;
                                                    document.getElementById('nameAsVoterIdDiv').innerHTML = "";
                                                    document.getElementById('nameAsPanDiv').innerHTML = "";
                                                    document.getElementById('nameAsAdharDiv').innerHTML = "";
                                                     document.getElementById('adharDiv').innerHTML = "";
                                                     document.getElementById('panDiv').innerHTML = "";
                                                  //   document.getElementById('fileDiv').innerHTML="";
                                                    document.getElementById('nameAsdrivingLicenceDiv').innerHTML = "";
                                                    document.getElementById('drivingLicenceExpiryDateDiv').innerHTML = "";
                                                    document.getElementById('speciallyAbleCategoryDiv').innerHTML = "";
                                                    document.getElementById('nameAsRationDiv').innerHTML = "";
                                                    if ("" !== $('#voterId').val()) {
                                                        if ("" == $('#nameAsVoterId').val()) {
                                                            document.getElementById('nameAsVoterIdDiv').innerHTML = "required";
                                                            errorFlag++;
                                                        }

                                                        atLeastCountOne++;
                                                    }
                                                    
                                                    if ("" !== $('#panNo').val()) {
                                                        
                                                        if ("" == $('#nameAsPan').val()) {
                                                            document.getElementById('nameAsPanDiv').innerHTML = "*required";
                                                            errorFlag++;
                                                        }
                                                        atLeastCountOne++;
                                                    }
                                                    if ("" !== $('#panNo').val()) {
                                                        
                                                        var panNo=$('#panNo').val();
                                                         var regpan = /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/;
                                                         if (regpan.test(panNo)== false) {
                                                            document.getElementById('panDiv').innerHTML = "Enter PAN number correctly";
                                                            errorFlag++;
                                                            }
                                                       
                                                        atLeastCountOne++;
                                                    }
                                                    if ("" !== $('#adhar').val()) {
                                                       // alert("adhar val "+$('#nameAsAdhar').val());
                                                        if ("" == $('#nameAsAdhar').val()) {
                                                            document.getElementById('nameAsAdharDiv').innerHTML = "*required";
                                                            errorFlag++;
                                                        }
                                                        atLeastCountOne++;
                                                    }
                                                   
                                                    if ("" !== $('#adhar').val()) {
                                                        
                                                        var adharNo=$('#adhar').val();
                                                       if (adharNo.match(/[a-z]/i) || adharNo.match(/[^\w\s]/gi) || (adharNo.length!=12)) {
                                                       // if ("" == $('#nameAsAdhar').val()) {
                                                            document.getElementById('adharDiv').innerHTML = "Enter Aadhar number correctly";
                                                            errorFlag++;
                                                        }
                                                        atLeastCountOne++;
                                                    }
                                                   
                                                    
//                                                     if ("" !== $('#file').val()) {
//                                                         
//                                                        if ("" != $('#file').val()) {
//                                                          //  alert("test !!!!");
//                                                          var ext = $('#file').val().split('.').pop().toLowerCase();
//                                                          if($.inArray(ext, ['gif','png','jpg','jpeg','pdf']) == -1) {
//                                                            //alert('Please upload file gif,png,jpg,jpeg format.');
//                                                            document.getElementById('fileDiv').innerHTML = "Please upload in gif,png,jpg,jpeg format";
//                                                            errorFlag++;
//                                                                } 
//                                                        }
//                                                        atLeastCountOne++;
//                                                    }
                                                    if ("" !== $('#rationId').val()) {
                                                        if ("" == $('#nameAsRation').val()) {
                                                              document.getElementById('nameAsRationDiv').innerHTML = "*required";
                                                            errorFlag++;
                                                        }
                                                        atLeastCountOne++;
                                                    }
                                                    if ("" !== $('#drivingLicence').val()) {

                                                        if ("" === $('#nameAsdrivingLicence').val()) {
                                                            document.getElementById('nameAsdrivingLicenceDiv').innerHTML = "*required";
                                                            errorFlag++;
                                                        }
                                                        if ("" === $('#drivingLicenceExpiryDate').val()) {
                                                            document.getElementById('drivingLicenceExpiryDateDiv').innerHTML = "*required";
                                                            errorFlag++;
                                                        }

                                                        atLeastCountOne++;
                                                    }
                                                    if ($('#speciallyAbleYes').is(':checked')) {
                                                        if ("" === $('#speciallyAbleCategory').val()) {
                                                            document.getElementById('speciallyAbleCategoryDiv').innerHTML = "*required";
                                                            errorFlag++;
                                                        }
                                                    }
                                                    if (atLeastCountOne < 2) {
                                                        alert("Please enter atleast two Id Proof details");
                                                        errorFlag++;
                                                    }




                                                    if (errorFlag == 0) {

                                                        return true;
                                                    } else {
                                                        return false;
                                                    }

                                                }
</script>