<script type="text/javascript">
 function dateOfRelValidation(rowId)
 {
    $("#dateOfRel"+rowId).datepicker({
                    changeYear: true,
                    changeMonth: true,
                    dateFormat:'dd-mm-yy',
                    showButtonPanel: false,
                    maxDate: "0"
                });
    
 }
 function dateOfJoinValidation(rowId)
 {
     $("#dateOfJoin"+rowId).datepicker({
                    changeYear: true,
                    changeMonth: true,
                    dateFormat:'dd-mm-yy',
                    showButtonPanel: false,
                    minDate: "-60Y", maxDate: "0",
                    onSelect: function(dateText, inst) {
                        $("#dateOfRel0").datepicker("option" ,"minDate", new Date(dateText.split("-")[2], --dateText.split("-")[1] ,dateText.split("-")[0]));
                    }
                });
 }
 function checkValue(selectObject){
        <%--alert($(selectObject).parent().find('.lifeBox').val());--%>
        if(selectObject.checked){
              $(selectObject).parent().find('.lifeBox').val('1');
        }else{
              $(selectObject).parent().find('.lifeBox').val('0');
        }
    }
        function changePage(pageToChange){
            var pageCount=4;
            for(count=1;count<=pageCount;count++){
            $("#page"+count).attr("class","hidden");
            }
            $("#page"+pageToChange).attr("class","visible");
        }

        function addRow(tableName,hiddenMasterId){
        $(document).ready(function(){
        alert($("#"+tableName+" tr").length);
        var noOfRows = $("#"+tableName+" tr").length - 1;
       // alert(noOfRows);
        if(tableName == 'previousEmployment'){
            dataToAdd = getPrevEmpData(noOfRows);
        }else if(tableName == 'technicalSkills'){
            dataToAdd = getTechnicalSkillsData(noOfRows);
        }else if(tableName == 'familyMember'){
            dataToAdd = getFamilyMemberData(noOfRows);
        }else if(tableName == 'personsAtCompany'){
           
            dataToAdd = getPersonsAtCompany(noOfRows);
        }else if(tableName == 'refFrmEarlierCompany'){
             alert("inside referrer");
            dataToAdd = getPrevEmployerRef(noOfRows);
        }
        else if(tableName == 'educationalQualification'){
            dataToAdd  = getEducationalQualification(noOfRows)
        }
        else if(tableName == 'visaDetails'){
            dataToAdd  = getVisaDetails(noOfRows)
        }
        else if(tableName == 'educationalQualificationFileAttachment'){
            dataToAdd  = getEducationalQualificationProof(noOfRows)
            //alert(noOfRows);
       }
<%--alert("Here Comes...");--%>
      
if(tableName == "educationalQualificationFileAttachment"){
        $("#"+tableName+" tr:last").after('<tr>'+dataToAdd+'<td><input type="hidden" name="hiddenMasterId" id="'+hiddenMasterId+noOfRows+'" value="" /><input type="hidden" class="statusCheck" id="'+tableName+'Status'+noOfRows+'" name="'+tableName+'Status" /></td></tr>');
}else{
        $("#"+tableName+" tr:last").after('<tr>'+dataToAdd+'<td><input type="hidden" name="'+hiddenMasterId+'" id="'+hiddenMasterId+noOfRows+'" value="" /><input type="hidden" class="statusCheck" id="'+tableName+'Status'+noOfRows+'" name="'+tableName+'Status[]" /><img src="${pageContext.request.contextPath}/css/images/delete-icon.png" onclick="removeRow(this,'+noOfRows+',\''+tableName+'\')" /></td></tr>');}

        $('#'+tableName+'Status'+noOfRows).val("undeleted");
        $("#"+tableName+"Count").val(noOfRows);
                 <%--$(".datepicker").datepicker({
                    changeYear: true,
                    changeMonth: true,
                    dateFormat:'dd-mm-yy',
                    showButtonPanel: true
                });--%>
              <%--  $(".datePickerYear").datepicker( {
                    changeMonth: false,
                    changeYear: true,
                    showButtonPanel: false,
                    dateFormat: 'yy'
                });--%>
                  $("#dobRelation"+noOfRows).datepicker({
                    changeYear: true,
                    changeMonth: true,
                    dateFormat:'dd-mm-yy',
                    showButtonPanel: false,
                    minDate: "-60Y", maxDate: "0"
                });
                 $("#dateOfRel"+noOfRows).datepicker({
                    changeYear: true,
                    changeMonth: true,
                    dateFormat:'dd-mm-yy',
                    showButtonPanel: false,
                    maxDate: "0"
                });
          $("#dateOfJoin"+noOfRows).datepicker({
                    changeYear: true,
                    changeMonth: true,
                    dateFormat:'dd-mm-yy',
                    showButtonPanel: true,
                    minDate: "-60Y", maxDate: "0",
                    onSelect: function(dateText, inst) {
                        $(this).parent().parent().find(".dateOfRel").datepicker("option" ,"minDate", new Date(dateText.split("-")[2], --dateText.split("-")[1] ,dateText.split("-")[0]));
                    }
                });
                $("#visaDateOfExpiry"+noOfRows).datepicker({
                    changeYear: true,
                    changeMonth: true,
                    dateFormat:'dd-mm-yy',
                    showButtonPanel: true
                    //minDate: "0", maxDate: "0"
                });
        $("#visaDateOfIssue"+noOfRows).datepicker({
                    changeYear: true,
                    changeMonth: true,
                    dateFormat:'dd-mm-yy',
                    showButtonPanel: true,
                    minDate: "-60Y", maxDate: "0",
                    onSelect: function(dateText, inst) {
                        $("#visaDateOfExpiry"+noOfRows).datepicker("option" ,"minDate", new Date(dateText.split("-")[2], --dateText.split("-")[1] ,dateText.split("-")[0]));
                    }
                });
            $(".datePickerYear").each(function(){
            $(this).rules("add", {
                  min: 1910,
                  max:2100,
                  minlength: 4,
                  digits: true
            });
        });
        });
      }

      function removeRow(rowToRemove,rowNo,tableName){
      //$(rowToRemove).parent().parent().find("input:last").val("deleted");
      $("#"+tableName+"Status"+rowNo).val("deleted");
      $(rowToRemove).parent().parent().attr("class","hidden");
      }

      function getPrevEmpData(rowId){
          var dataToAdd = '<td><textarea cols="3" rows="2" name="nameAddPrevEmp" id="nameAddPrevEmp'+rowId+'"></textarea></td><td><input type="text" name="dateOfJoin[]" id="dateOfJoin'+rowId+'" class="" readonly /></td><td><input type="text" name="desigOnJoin[]" id="desigOnJoin'+rowId+'" class="alphaWithDot" /></td><td><input type="text" name="salaryOnJoin[]" id="salaryOnJoin'+rowId+'" class="numberWithdot" /></td><td><input type="text" name="dateOfRel[]" id="dateOfRel'+rowId+'" class="dateOfRel" readonly /></td><td><input type="text" name="desigOnRel[]" id="desigOnRel'+rowId+'" class="alphaWithDot" /></td><td><input type="text" name="salOnRel[]" id="salOnRel'+rowId+'" class="numberWithdot" /></td>';
          return dataToAdd;
      }

     <%-- function getEducationalQualification(rowId){
          var dataToAdd = '<td><input type="text" name="otherQualificationDetails" id="otherQualification'+rowId+'" class="" /></td><td><input type="text" name="otherYearOfPassingDetails" id="otherYearOfPassing'+rowId+'" class="datePickerYear" readonly /></td><td><input type="text" name="otherInstitutionDetails" id="otherInstitution'+rowId+'" class="" /></td><td><input type="text" name="otherPercentageDetails" id="otherPercentage'+rowId+'" class="" /></td><td><input type="file" name="otherProofDetails" id="otherProof'+rowId+'" value="" class="" /></td><td></td>';
          return dataToAdd;
      }--%>

      function getEducationalQualification(rowId){
          var dataToAdd = '<td><input type="text" class="qualificationCheck" name="otherQualification" id="otherQualification'+rowId+'" class="" /></td><td><input type="text" name="otherYearOfPassing[]" id="otherYearOfPassing'+rowId+'" class="datePickerYear number" maxlength=4 style="width: 40px;" /></td><td><input type="text" name="otherInstitution[]" id="otherInstitution'+rowId+'" class="alphaWithDot" /></td><td><input type="text" name="otherPercentage[]" id="otherPercentage'+rowId+'" class="number" maxlength="5" style="width: 40px;" /></td>';
          return dataToAdd;
      }
      
      function getVisaDetails(rowId){
          var dataToAdd = '<td><input type="text" name="visaCountry" id="visaCountry'+rowId+'" class="" /></td><td><input type="text" name="visaType[]" id="visaType'+rowId+'" /></td><td><input type="text" name="visaDateOfIssue[]" id="visaDateOfIssue'+rowId+'" readonly /></td><td><input type="text" readonly name="visaDateOfExpiry[]" id="visaDateOfExpiry'+rowId+'" /></td>';
          return dataToAdd;
      }
      
      function getEducationalQualificationProof(rowId){
          var dataToAdd = '<td style="width:400px;"><input type="file" name="otherProof'+rowId+'" id="otherProof'+rowId+'" class="" /></td>';
          return dataToAdd;
      }

      function getTechnicalSkillsData(rowId){
          var dataToAdd = '<td><input type="text" name="languages" id="languages'+rowId+'" class="" onkeyup="checkMaxLength(this.id,this.value)" /></td><td><input type="text" name="skilldatabases[]" id="databases'+rowId+'" class="" /></td><td><input type="text" name="platform[]" id="platform'+rowId+'" class="" /></td><td><input type="text" name="operatingSystem[]" id="operatingSystem'+rowId+'" class="" /></td>';
          return dataToAdd;
      }

      function getFamilyMemberData(rowId){
          //var dataToAdd = '<td><input type="text" name="nameOfRelation'+rowId+'" id="nameOfRelation'+rowId+'" class="" /></td><td><input type="text" name="relationShip'+rowId+'" id="relationShip'+rowId+'" class="" /></td><td><input type="text" name="dobRelation'+rowId+'" id="dobRelation'+rowId+'" class="datepicker" readonly /></td><td><input type="text" name="occupationOfRel'+rowId+'" id="occupationOfRel'+rowId+'"  /></td>';
          <%--var dataToAdd = '<td><input type="text" name="nameOfRelation[]" id="nameOfRelation'+rowId+'" class="" /></td><td><select name="relationShip[]" id="relationShip'+rowId+'"><option  value="">-- Select Relationship --</option><c:forEach items="${relationShips}" var="relationShipValue" varStatus="relationShipindex"><option value="${relationShipindex.index}" >${relationShipValue}</option></c:forEach></select></td><td><input type="text" name="dobRelation[]" id="dobRelation'+rowId+'" class="" readonly /></td><td><input type="text" name="occupationOfRel[]" id="occupationOfRel'+rowId+'"  /></td><td><input type="checkbox" name="lifeInsuranceCheck[]" id="lifeInsuranceCheck'+rowId+'" onclick="checkValue(this)"  /><input type="hidden" name="lifeInsurance[]" class="lifeBox" id="lifeInsurance'+rowId+'" value="0" /></td><td><input type="checkbox" name="medicalInsuranceCheck[]" id="medicalInsuranceCheck'+rowId+'" onclick="checkValue(this)" /><input type="hidden" name="medicalInsurance[]" class="lifeBox" id="medicalInsurance'+rowId+'" value="0" /></td><td><input type="checkbox" name="paymentOfGratuityCheck[]" id="paymentOfGratuityCheck'+rowId+'" onclick="checkValue(this)"  /><input type="hidden" name="paymentOfGratuity[]" class="lifeBox" id="paymentOfGratuity'+rowId+'" value="0" /></td>';--%>
          var dataToAdd = '<td><input type="text" name="nameOfRelation" id="nameOfRelation'+rowId+'" class="" /></td><td><select name="relationShip[]" id="relationShip'+rowId+'"><option  value="">-- Select Relationship --</option><c:forEach items="${relationShips}" var="relationShipValue" varStatus="relationShipindex"><option value="${relationShipindex.index}" >${relationShipValue}</option></c:forEach></select></td><td><input type="text" name="dobRelation[]" id="dobRelation'+rowId+'" class="" readonly /></td><td><input type="text" name="occupationOfRel[]" id="occupationOfRel'+rowId+'"  /></td><td><input type="checkbox" name="medicalInsuranceCheck[]" id="medicalInsuranceCheck'+rowId+'" onclick="checkValue(this)" /><input type="hidden" name="medicalInsurance[]" class="lifeBox" id="medicalInsurance'+rowId+'" value="0" /></td>';
          return dataToAdd;
      }

     function getPersonsAtCompany(rowId){
        alert(rowId);  
        var dataToAdd = '<td><input type="text" name="nameOfPersonAtCom" id="nameOfPersonAtCom'+rowId+'" class="" /><div id="name_err'+rowId+'" style="color:red;display: none">Only Alphabets are allowed</div></td><td><input type="text" name="companyAndDep" id="companyAndDep'+rowId+'" class="" /><div id="company_err'+rowId+'" style="color:red;display: none">Only AlphaNumerics are allowed</div></td><td><input type="text" name="pacRelation" id="pacRelation'+rowId+'" /><div id="pacRelation_err'+rowId+'" style="color:red;display: none">Only Alphabets are allowed</div></td>';
          return dataToAdd;
      }

     function getPrevEmployerRef(rowId){
          var dataToAdd = '<td><input type="text" name="refFrmEarName" id="refFrmEarName'+rowId+'" class="" /></td>'
  +'<td><input type="text" name="refFrmEarCmpName" id="refFrmEarCmpName'+rowId+'" class="" /></td>'
  +'<td><input type="text" name="refFrmEarAddressAndTel" id="refFrmEarAddressAndTel'+rowId+'" class="number" maxlength="20" /></td>'
  +'<td><input type="text" name="refFrmEarDesignation" id="refFrmEarDesignation'+rowId+'" class="" /></td>'
  +'<td><input type="text" name="refFrmEarNoOfYearsKnown" id="refFrmEarNoOfYearsKnown'+rowId+'" class="number" maxlength="2" /></td>'
  +'<td><input type="hidden" name="refFrmEarlierCompanyStatus" id="refFrmEarlierCompanyStatus'+rowId+'" value="undeleted" />'
       +'<input type="hidden" name="jfRefEarCompId" id="jfRefEarCompId'+rowId+'" value="0" /></td>';
          return dataToAdd;
      }
function calculateGrandTotal()
{
    var totalA=document.getElementById('asTotalPerAnnum').value;
    var totalB=document.getElementById('abTotalPerAnnum').value;
    //alert(isNaN(totalA)+"----"+isNaN(totalB));
    document.getElementById('grandTotalPerAnnum').value=(parseFloat(totalA)+parseFloat(totalB));

}

function addRow_Professional(){
    
    
    if (professional_validation()) {
        var trVal = "'PRF_TR'";
        var currentRow = $('#referrerPrev_count').val();
        var rowId = parseInt($('#referrerPrev_count').val()) + 1;
//        alert(rowId);
        $('#referrerPrev_count').val(rowId);
        var tr = '';
        tr += '<tr id="PRF_TR_' + rowId + '">';
        tr += '   <td>';
        tr += '	<input type="text" name="refFrmEarName" id="refFrmEarName' + rowId + '" class="required" />';
        tr += '<span id="refFrmEarName_error_' + rowId + '" style="color:red;display: none;">*required</span>';
        tr += ' <div id="refFrmEarName_err_' + rowId + '" style="color:red;display: none;">Only Alphabets are allowed</div>';
        tr += '   </td>';
        tr += '   <td>';
        tr += '	<input type="text" name="refFrmEarCmpName" id="refFrmEarCmpName' + rowId + '" class="required" />';
        tr += '<span id="refFrmEarCmpName_error_' + rowId + '" style="color:red;display: none;">*required</span>';
        tr += ' <div id="refFrmEarCmpName_err_' + rowId + '" style="color:red;display: none;">Only Alphabets are allowed</div>';
        tr += '   </td>';
        tr += '   <td>';
        tr += '	<input type="text" name="refFrmEarAddressAndTel" id="refFrmEarAddressAndTel' + rowId + '" class="number required" maxlength="20" style="width:100px"/>';
        tr += '<span id="refFrmEarAddressAndTel_error_' + rowId + '" style="color:red;display: none;">*required</span><div id="refFrmEarAddressAndTel_err_' + rowId + '" style="color:red;display: none;">*Invalid Number</div>';
        tr += '   </td>';
        tr += '   <td>';
        tr += '	<input type="text" name="refFrmEarDesignation" id="refFrmEarDesignation' + rowId + '" class="required" />';
        tr += '<span id="refFrmEarDesignation_error_' + rowId + '" style="color:red;display: none;">*required</span>';
        tr += ' <div id="refFrmEarDesignation_err_' + rowId + '" style="color:red;display: none;">Only AlphaNumerics are allowed</div>';
        tr += '   </td>';
        tr += '   <td>';
        tr += '	<input type="text" name="refFrmEarNoOfYearsKnown" id="refFrmEarNoOfYearsKnown' + rowId + '" class="number required" maxlength="2" style="width:100px"/>';     
        tr += '<span id="refFrmEarNoOfYearsKnown_error_' + rowId + '" style="color:red;display: none;">*required</span><div id="refFrmEarNoOfYearsKnown_err_' + rowId + '" style="color:red;display: none;">*Invalid Number</div>';
        tr += '   </td>';
        
        tr += '   <td>';
        tr += '	<input type="text" name="refFrmEarEmailId" id="refFrmEarEmailId' + rowId + '"  />';     
        tr += '<span id="refFrmEarEmailId_error_' + rowId + '" style="color:red;display: none;">*required</span><div id="refFrmEarEmailId_err_' + rowId + '" style="color:red;display: none;">*Invalid Email Id</div>';
        tr += '   </td>';
        
        tr += '   <td>';
        tr += '	<input type="hidden" name="refFrmEarlierCompanyStatus" id="refFrmEarlierCompanyStatus' + rowId + '" value="undeleted" />';
        tr += '	<input type="hidden" name="jfRefEarCompId" id="jfRefEarCompId' + rowId + '" value="0" /><input type="hidden" name="PRF_TR_deleted" id="PRF_TR_deleted' + rowId + '" value="0" />';
        tr += '<img alt="remove" src="' + $('#base_path').val() + '/css/images/delete-icon.png" onclick="deleteRow_PRF(' + trVal + ',' + rowId + ',0)" />';
        tr += '   </td>';
        tr += '</tr>';
        tr = tr.replace(/selected="selected"/gi, "");
        tr = tr.replace(/selected/gi, "");
        $('#PRF_TR_' + currentRow).after(tr);
    }
    
//    function deleteRow1(tr,row,val){
//        alert("inside delete function");
//        if (val == 1) {
//        var con = confirm("Are you sure want to delete ?");
//        if (con) {
//            $('#' + tr + '_' + row).hide();
//            $('#refFrmEarlierCompanyStatus' + row).val('deleted');
////            $('#education_recordCount').val(row-1);
//            return true;
//        } else {
//            return false;
//        }
//    } else {
//        $('#' + tr + '_' + row).hide();
//        $('#refFrmEarlierCompanyStatus' + row).val('deleted');
////        alert($('#' + tr + '_deleted_' + row).val());
////        $('#education_recordCount').val(row-1);
//        return true;
//    }
//    }
}

function addRow_Relatives(){
    
    
    if (personsknown_company_validation()) {
        var trVal = "'REL_TR'";
        var currentRow = $('#friends_recordCount').val();
        var rowId = parseInt($('#friends_recordCount').val()) + 1;
//        alert(rowId);
        $('#friends_recordCount').val(rowId);
        var tr = '';
        tr += '<tr id="REL_TR_' + rowId + '">';
        tr += '   <td>';
        tr += '	<input type="text" name="nameOfPersonAtCom" id="nameOfPersonAtCom' + rowId + '" class="" />';
        tr += '<div id="name_err' + rowId + '" style="color:red;display: none">Only Alphabets are allowed</div>';      
        tr += '   </td>';
        tr += '   <td>';
        tr += '	<input type="text" name="companyAndDep" id="companyAndDep' + rowId + '" class="" />';
        tr += '<div id="company_err' + rowId + '" style="color:red;display: none">Only AlphaNumerics are allowed</div>';       
        tr += '   </td>';
        tr += '   <td>';
        tr += '	<input type="text" name="pacRelation" id="pacRelation' + rowId + '" />';
        tr += '<div id="pacRelation_err' + rowId + '" style="color:red;display: none">Only Alphabets are allowed</div>';
        tr += '   </td>';
        tr += '   <td>';
        tr += '	<input type="hidden" name="personsAtCompanyStatus" id="personsAtCompanyStatus' + rowId + '" value="undeleted" />';
        tr += '<input type="hidden" name="jfPerCompId" id="jfPerCompId' + rowId + '" value="0" /><input type="hidden" name="REL_TR_deleted" id="REL_TR_deleted' + rowId + '" value="0" />';       
        tr += '<img alt="remove" src="' + $('#base_path').val() + '/css/images/delete-icon.png" onclick="deleteRow_REL(' + trVal + ',' + rowId + ',0)" />';
        tr += '   </td>';
        tr += '</tr>';
        tr = tr.replace(/selected="selected"/gi, "");
        tr = tr.replace(/selected/gi, "");
        $('#REL_TR_' + currentRow).after(tr);
    }
}

function deleteRow_PRF(tr, row, val) {
       //alert("inside delete function");
                if (val == 1) {
                    var con = confirm("Are you sure want to delete ?");
                    if (con) {
                        $('#' + tr + '_' + row).hide();
                        $('#refFrmEarlierCompanyStatus'+ row).val('deleted');
                        $('#' + tr + '_deleted' + row).val(1);
                        //alert(row);
            //alert($('#' + tr + '_deleted' + row).val());
//            $('#education_recordCount').val(row-1);
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    $('#' + tr + '_' + row).hide();
                    $('#refFrmEarlierCompanyStatus'+ row).val('deleted');
                    $('#' + tr + '_deleted' + row).val(1);
                    //alert(row);
        //alert($('#' + tr + '_deleted' + row).val());
//        alert($('#' + tr + '_deleted_' + row).val());
//        $('#education_recordCount').val(row-1);
                    return true;
                }
            }
            
            function deleteRow_REL(tr, row, val) {
       //alert("inside delete function");
                if (val == 1) {
                    var con = confirm("Are you sure want to delete ?");
                    if (con) {
                        $('#' + tr + '_' + row).hide();
                        $('#personsAtCompanyStatus'+ row).val('deleted');
                        $('#' + tr + '_deleted' + row).val(1);
                        //alert(row);
            //alert($('#' + tr + '_deleted' + row).val());
//            $('#education_recordCount').val(row-1);
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    $('#' + tr + '_' + row).hide();
                    $('#personsAtCompanyStatus'+ row).val('deleted');
                    $('#' + tr + '_deleted' + row).val(1);
                    //alert(row);
        //alert($('#' + tr + '_deleted' + row).val());
//        alert($('#' + tr + '_deleted_' + row).val());
//        $('#education_recordCount').val(row-1);
                    return true;
                }
            }

    </script>
