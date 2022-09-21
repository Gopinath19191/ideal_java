<%-- 
    Document   : appraisaltrigger
    Created on : Nov 12, 2010, 6:49:49 PM
    Author     : HARIHARAN.C
--%>

<%@include file="../../../../../header.jsp" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<c:if test="${groupId!='5'}">
    <c:redirect url="/unauthorisedaccess.jsp"></c:redirect>
</c:if>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Annual Appraisal Trigger </title>
    <script type="text/javascript">
        var appraiserIdBulkChange = 0;
        var reviewerIdBulkChange = 0;
        var normalizerIdBulkChange = 0;
        $(document).ready(function() {
           
            
            $("#employeeFilterForm").validate({
                errorElement:"div",
                errorClass:"customError"
            });


            $( "#dialog-form" ).dialog({
                autoOpen: false,
                height: 200,
                width: 380,
                modal: true
            });

            $( "#create-user" ).click(function() {
                var bulkId= null;
                $(".checkSelectBox").each(
                function(){

                    if(this.checked){
                        if(bulkId!=null){
                            bulkId  =   bulkId+","+this.value;
                        }else{
                            bulkId  =   this.value;
                        }
                    }
                }
            )
                $("#bulkId").val(bulkId);
                $( "#dialog-form" ).dialog( "open" );
            });

            $("input#AppraiserBulk").autocomplete("getEmployeeName.do", {
                scroll: true,
                scrollHeight: 400,
                width:200,
                autoFill:true
            });
            $("input#ReviewerBulk").autocomplete("getEmployeeName.do", {
                scroll: true,
                scrollHeight: 400,
                width:200,
                autoFill:true
            });
            $("input#NormalizerBulk").autocomplete("getEmployeeName.do", {
                scroll: true,
                scrollHeight: 400,
                width:200,
                autoFill:true
            });
            
            $("input#AppraiserBulk").result(function(event, data, formatted) {
                appraiserIdBulkChange = data[1];
                
            });
            $("input#ReviewerBulk").result(function(event, data, formatted) {
                reviewerIdBulkChange = data[1];

            });
            $("input#NormalizerBulk").result(function(event, data, formatted) {
                normalizerIdBulkChange = data[1];

            });




        });
        
	
        function toggleSelect(selectedValue){
                
            $(".checkSelectBox").each(
            function(){
                this.checked = selectedValue.checked;
                disableFields(this);
            }
        )
        }

        function disableFields(selectedElement){
            if(selectedElement.checked){
                //alert('true');
                $("#appraiseeIdCheck"+selectedElement.value).attr("disabled",false);
                $("#appraiserIdCheck"+selectedElement.value).attr("disabled",false);
                $("#reviewerIdCheck"+selectedElement.value).attr("disabled",false);
                $("#normalizerIdCheck"+selectedElement.value).attr("disabled",false);
                $("#bandIdCheck"+selectedElement.value).attr("disabled",false);
                $("#depIdCheck"+selectedElement.value).attr("disabled",false);
                $("#qpdIdCheck"+selectedElement.value).attr("disabled",false);
            }else{
                //alert('false');
                $("#appraiseeIdCheck"+selectedElement.value).attr("disabled",true);
                $("#appraiserIdCheck"+selectedElement.value).attr("disabled",true);
                $("#reviewerIdCheck"+selectedElement.value).attr("disabled",true);
                $("#normalizerIdCheck"+selectedElement.value).attr("disabled",true);
                $("#bandIdCheck"+selectedElement.value).attr("disabled",true);
                $("#depIdCheck"+selectedElement.value).attr("disabled",true);
                $("#qpdIdCheck"+selectedElement.value).attr("disabled",true);
            }
        }

        function changeAppraiseeData(appraiseeId){
            document.getElementById("appraiserDiv"+appraiseeId).style.display = 'none';
            document.getElementById("reviewerDiv"+appraiseeId).style.display = 'none';
            document.getElementById("normalizerDiv"+appraiseeId).style.display = 'none';
                
            $("#appraiserAutoDiv"+appraiseeId).show();
            $("#reviewerAutoDiv"+appraiseeId).show();
            $("#normalizerAutoDiv"+appraiseeId).show();
                    
            $("input#appraiserAuto"+appraiseeId).attr("disabled",false);
            $("input#reviewerAuto"+appraiseeId).attr("disabled",false);
            $("input#normalizerAuto"+appraiseeId).attr("disabled",false);
                
    
            $("input#appraiserAuto"+appraiseeId).autocomplete("getEmployeeName.do", {
                    scroll: true,
                    width:200,
                    scrollHeight: 400,
                    autoFill:true
                }
            );
            $("input#appraiserAuto"+appraiseeId).result(function(event, data, formatted) {
                $("#appraiserIdCheck"+appraiseeId).val(data[1]);
                $("#appraiserDiv"+appraiseeId).show();
                $("#appraiserDiv"+appraiseeId).html(data[0].split("-")[1]);
                $("#appraiserAutoDiv"+appraiseeId).hide();
                //document.getElementById("appraiserAutoDiv"+appraiseeId).style.display = 'none';
                  
            });


            $("input#reviewerAuto"+appraiseeId).autocomplete("getEmployeeName.do", {
                scroll: true,
                scrollHeight: 400,
                width:200,
                autoFill:true
            });
            $("input#reviewerAuto"+appraiseeId).result(function(event, data, formatted) {
                $("#reviewerIdCheck"+appraiseeId).val(data[1]);
                $("#reviewerDiv"+appraiseeId).show();
                $("#reviewerDiv"+appraiseeId).html(data[0].split("-")[1]);
                $("#reviewerAutoDiv"+appraiseeId).hide();
            });
                
                
            $("input#normalizerAuto"+appraiseeId).autocomplete("getEmployeeName.do", {
                scroll: true,
                scrollHeight: 400,
                width:200,
                autoFill:true
            });
            $("input#normalizerAuto"+appraiseeId).result(function(event, data, formatted) {
                $("#normalizerIdCheck"+appraiseeId).val(data[1]);
                $("#normalizerDiv"+appraiseeId).show();
                $("#normalizerDiv"+appraiseeId).html(data[0].split("-")[1]);
                $("#normalizerAutoDiv"+appraiseeId).hide();
            });
        }

        function noenter() {
            return !(window.event && window.event.keyCode == 13);
        }

        function exportToExcel(){
            document.getElementById("appraisalTriggerForm").action="excelExport.do";
            document.getElementById("appraisalTriggerForm").submit();
        }

        function warningPrompt(){
            return confirm("Any unsaved data will be lost.\n Do u wish to Continue exporting to excel?");
        }

        function disableButtons(){
            $(".buttons").each(
                        function(){
                            $(this).hide();
                        }
                    );
        }
    $("appraisalTriggerForm").submit(function() {
      $(".buttons").each(
            function(){$(this).hide();}
        );
      return true;
    });
    </script>
</head>
<body onkeypress="return noenter()">
    <div class="tabletools submenuwrap">
        <%@include file="../../../../../menu.jsp" %>
        <div class="contentwrap">
            <div class="contentBand">Triggering Appraisal</div>
            <div class="contentarea">
                <form action="begin.do" method="POST" name="employeeFilterForm" id="employeeFilterForm" >
                    <table width="100%">
                        <tr>
                            <td><label class="requiredLabel">Year:</label></td>
                            <td>
                                <select name="appraisalYearFilter" id="appraisalYear" class="required" >
                                    <option value="">-- Select Year --</option>
                                    <c:forEach items="${yearData}" var="year">
                                        <c:choose>
                                            <c:when test="${appraisalYear == year}">
                                                <option value="${year}" selected="selected">${year}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${year}">${year}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </td>
                            <%--<td><label class="requiredLabel">Period:</label></td>
                            <td>
                                <select name="appraisalPeriodFilter" id="appraisalPeriod" class="required">
                                    <option value="">-- Select period --</option>
                                    <c:forEach begin="1" end="4" var="qValue">
                                        <c:choose>
                                            <c:when test="${appraisalQuarter == qValue}">
                                                <option value="${qValue}" selected="selected">Q${qValue}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${qValue}">Q${qValue}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>

                            </td>--%>
                            <td><label class="requiredLabel">Employee Status:</label></td>
                            <td>

                                <%--<c:out value="${filterData.employeeStatus[0]}"></c:out>--%>


                                <select name="employeeStatusFilter" id="employeeStatus" multiple size="4" class="required" >
                                    <c:forEach items="${employeeStatus}" var="entry">
                                        <c:set var="checkedStatus" value=""></c:set>
                                        <c:forEach items="${filterData.employeeStatusFilter}" var="datum">
                                            <c:if test="${datum==entry.key}"><c:set var="checkedStatus" value="selected=selected"></c:set></c:if>
                                        </c:forEach>
                                        <option value="${entry.key}" ${checkedStatus} >${entry.value}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><label class="requiredLabel">Company Structure:</label></td>
                            <td>
                                <select name="companyStructureFilter" id="companyStructure" multiple size="4" class="required" >
                                    <c:forEach items="${companyStructure}" var="StructureDesc">
                                        <c:set var="selectStatus" value=""></c:set>
                                        <c:forEach items="${filterData.companyStructureFilter}" var="csdatum">
                                            <c:if test="${csdatum==StructureDesc.csId}"><c:set var="selectStatus" value="selected=selected"></c:set></c:if>
                                        </c:forEach>
                                        <option value="${StructureDesc.csId}" ${selectStatus}>${StructureDesc.structureName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><label class="requiredLabel">Band:</label></td>
                            <td>
                                <select name="bandDataFilter" id="bandData" multiple size="4" class="required" >
                                    <c:forEach items="${bandData}" var="band">
                                        <c:set var="selectStatus" value=""></c:set>
                                        <c:forEach items="${filterData.bandDataFilter}" var="banddatum">
                                            <c:if test="${banddatum==band.masterBandId}"><c:set var="selectStatus" value="selected=selected"></c:set></c:if>
                                        </c:forEach>
                                        <option value="${band.masterBandId}" ${selectStatus}>${band.masterBandName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><label class="requiredLabel">Appraiser:</label></td>
                            <td>
                                <select name="appraiserId" id="appraiserId"  >
                                    <option value="0">-- Select Appraiser --</option>
                                    <c:forEach items="${appraiserData}" var="appraiser">
                                        <c:set var="selectStatus" value="" />
                                        <c:if test="${filterData.appraiserId eq appraiser.appraiserId}"><c:set var="selectStatus" value="selected=selected"></c:set></c:if>
                                        <option value="${appraiser.appraiserId}" ${selectStatus} >${appraiser.appraiserName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><input type="submit" class="buttons fetchbutton" value="Fetch Employees" class="toolfind"></td>
                        </tr>

                        <%--<c:set var="msgSucess" value="scss" ></c:set>--%>
                        <%--<c:if test="${param.msg eq msgSucess}">
                        <tr>
                            <td style="text-align: center" colspan="8" >
                                <font color="green">QPD Triggered Successfully</font>
                            </td>
                        </tr>
                        </c:if>--%>


                        <c:if test="${!empty(successMsg)}">
                            <tr>
                                <td style="text-align: center" colspan="8" >
                                    <font color="green">${successMsg}</font>
                                </td>
                            </tr>
                            <c:set var="exportData" value="${}" scope="session"></c:set>
                        </c:if>
                    </table>
                </form>
            </div>

            <div class="formarea">
                <form action="save.do" method="POST" name="appraisalTriggerForm" id="appraisalTriggerForm">
                    <c:if test="${!empty(appraiseeData)}">
                         <c:if test="${filterData.appraisalYearFilter==null}">
                        <input type="hidden" name="appraisalYearFinal" value="${appraisalYearSession}" />
                        </c:if>
                        <c:if test="${filterData.appraisalYearFilter!=null}">
                        <input type="hidden" name="appraisalYearFinal" value="${filterData.appraisalYearFilter}" />
                        </c:if>

                        <c:if test="${filterData.appraisalPeriodFilter==null}">
                        <input type="hidden" name="appraisalPeriodFinal" value="${appraisalQuarterSession}" />
                        </c:if>
                        <c:if test="${filterData.appraisalPeriodFilter!=null}">
                        <input type="hidden" name="appraisalPeriodFinal" value="${filterData.appraisalPeriodFilter}" />
                        </c:if>
                        <table width="100%">
                            <thead>
                                <tr class="formarea_header">
                                    <th style="text-align: center" class="checkBox"><input type="checkbox" onclick="toggleSelect(this)" class="checkBox" checked /></th>
                                    <th>Appraisee ID</th>
                                    <th>Appraisee Name</th>
                                    <th>Band</th>
                                    <th>SBU/Function</th>
                                    <th width="60px">DOJ</th>
                                    <th>Appraiser</th>
                                    <th>Reviewer</th>
                                    <th>Final Reviewer</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <c:forEach items="${appraiseeData}" var="details" varStatus="rowCount">
                                <c:if test="${(rowCount.index%2)==0}"><c:set var="rowClass" value="formarea_row1"></c:set></c:if>
                                <c:if test="${(rowCount.index%2)!=0}"><c:set var="rowClass" value="formarea_row2"></c:set></c:if>
                                <tr class="${rowClass}">
                                    <td style="text-align: center"><input type="checkbox" name="triggerCheckForm[]" class="checkBox checkSelectBox" value="${details.appraiseeId}" onclick="disableFields(this)" checked /></td>
                                    <td>
                                        ${details.appraiseeNumber}
                                        <input type="hidden" name="appraiseeIdForm[]" id="appraiseeIdCheck${details.appraiseeId}" value="${details.appraiseeId}" />
                                        <input type="hidden" name="bandIdForm[]" id="bandIdCheck${details.appraiseeId}" value="${details.bandId}" />
                                        <input type="hidden" name="depIdForm[]" id="depIdCheck${details.appraiseeId}" value="${details.depId}" />
                                        <input type="hidden" name="qpdIdForm[]" id="qpdIdCheck${details.appraiseeId}" value="${details.qpdId}" />
                                    </td>
                                    <td>
                                        ${details.appraiseeName}
                                    </td>
                                    <td>
                                        ${details.bandName}
                                    </td>
                                    <td>
                                        ${details.departmentName}
                                    </td>
                                    <td>
                                        ${details.dateOfJoin}
                                    </td>
                                    <td>
                                        <div id="appraiserDiv${details.appraiseeId}">
                                            <span id="appDiv${details.appraiseeId}" title="${details.appraiserNumber}">${details.appraiserName}</span>
                                        </div>
                                        <div id="appraiserAutoDiv${details.appraiseeId}" style="display:none">
                                            <input type="text" name="appraiserAuto${details.appraiseeId}" id="appraiserAuto${details.appraiseeId}" disabled value="${details.appraiserNumber}-${details.appraiserName}" />
                                        </div>
                                        <input type="hidden" name="appraiserIdForm[]" id="appraiserIdCheck${details.appraiseeId}" value="${details.appraiserId}" />
                                        <c:set var="bulkAppraiserId" value="${details.appraiserId}" />
                                        <c:set var="bulkAppraiserName" value="${details.appraiserNumber}-${details.appraiserName}" />
                                    </td>
                                    <td>
                                        <div id="reviewerDiv${details.appraiseeId}">
                                            <span id="revDiv${details.appraiseeId}" title="${details.reviewerNumber}">${details.reviewerName}</span>
                                        </div>
                                        <div id="reviewerAutoDiv${details.appraiseeId}" style="display:none">
                                            <input type="text" name="reviewerAuto${details.appraiseeId}" id="reviewerAuto${details.appraiseeId}" value="${details.reviewerNumber}-${details.reviewerName}" disabled />
                                        </div>
                                        <input type="hidden" name="reviewerIdForm[]" id="reviewerIdCheck${details.appraiseeId}" value="${details.reviewerId}" />
                                        <c:set var="bulkReviewerId" value="${details.reviewerId}" />
                                        <c:set var="bulkReviewerName" value="${details.reviewerNumber}-${details.reviewerName}" />
                                    </td>
                                    <td>
                                        <div id="normalizerDiv${details.appraiseeId}">
                                            <span id="norDiv${details.appraiseeId}" title="${details.normalizerNumber}">${details.normalizerName}</span>
                                        </div>
                                        <div id="normalizerAutoDiv${details.appraiseeId}" style="display:none">
                                            <input type="text" name="normalizerAuto${details.appraiseeId}" id="normalizerAuto${details.appraiseeId}" value="${details.normalizerNumber}-${details.normalizerName}" disabled />
                                        </div>
                                        <input type="hidden" name="normalizerIdForm[]" id="normalizerIdCheck${details.appraiseeId}" value="${details.normalizerId}" />
                                        <c:set var="bulkNormalizerId" value="${details.normalizerId}" />
                                        <c:set var="bulkNormalizerName" value="${details.normalizerNumber}-${details.normalizerName}" />
                                    </td>
                                    <td>
                                        <a href="javascript:void(0)" onClick="changeAppraiseeData(${details.appraiseeId})">Change</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="10" style="text-align: center">
                                    <input type="submit" class="buttons exportbutton" value="Export To Excel" name="excelExport" onclick="return warningPrompt()"/>
                                    <input type="submit" class="buttons savebutton" value="Save" name="saveAppraisal" onclick="disableButtons()" />
                                    <input type="submit" class="buttons triggerbutton" value="Trigger Appraisal" name="submitAppraisal" onclick="disableButtons()" />
                                    <input type="button" class="buttons savebutton" id="create-user" value="Bulk" style="display: none;"/>


                                </td>
                            </tr>
                        </table>
                    </c:if>
                        <c:if test="${empty(appraiseeData)}">
                           <div class="formarea">
         <table width="100%">
                   <thead>
                   <tr class="formarea_header">
                        <td style="text-align: center">
                            -- Employee data not available for this year --
                        </td>
                   </tr>
                   </thead>
         </table>
        </div>
                        </c:if>
                    <%-- <c:set var="filterData" value="${filterData}" scope="session"></c:set>--%>
                </form>
            </div>
        </div>
    </div>

    <div id="dialog-form" title="Bulk Change Appraisee Data">
        <%--<p class="validateTips">All form fields are required.</p>--%>
            <fieldset>
                <table>
                    <tr>
                <input type="hidden" name="bulkId" id="bulkId" value="" />
                <td>
                <label for="name">Appraiser</label>
                </td><td>
                <input type="text" name="AppraiserBulk" id="AppraiserBulk" value="${bulkAppraiserName}" />
                </td>
                </tr>
                <tr>
                    <td>
                <label for="ReviewerBulk">Reviewer</label>
                    </td>
                    <td>
                <input type="text" name="ReviewerBulk" id="ReviewerBulk" value="${bulkReviewerName}" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="NormalizerBulk">Final Reviewer</label>
                    </td>
                    <td>
                    <input type="text" name="NormalizerBulk" id="NormalizerBulk" value="${bulkNormalizerName}"  />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center">
                        <input type="button" class="buttons" name="bulkSubmit" value="Change" onclick="bulkSubmit2()" >
                    </td>
                </tr>
                </table>
            </fieldset>
    </div>
    <script type="text/javascript">
        if($("#appraiserId").val()!="0"){
            $("#create-user").attr("style","display:inline;");
        }else{
            $("#create-user").attr("style","display:none;");
        }
        function bulkSubmit2(){
       
            var id  =   $("#bulkId").val();
            id  =   id.split(",");
            
            for(var count =0;count<id.length;count++){
                if(appraiserIdBulkChange!="0"){
                    $("#appDiv"+id[count]).html($("#AppraiserBulk").val().split("-")[1]);
                    $("#appraiserAuto"+id[count]).val($("#AppraiserBulk").val());
                    $("#appraiserIdCheck"+id[count]).val(appraiserIdBulkChange);
                }
                if(reviewerIdBulkChange!="0"){
                    $("#revDiv"+id[count]).html($("#ReviewerBulk").val().split("-")[1]);
                    $("#reviewerAuto"+id[count]).val($("#ReviewerBulk").val());
                    $("#reviewerIdCheck"+id[count]).val(reviewerIdBulkChange);
                }
                if(normalizerIdBulkChange!="0"){
                    $("#norDiv"+id[count]).html($("#NormalizerBulk").val().split("-")[1]);
                    $("#normalizerAuto"+id[count]).val($("#NormalizerBulk").val());
                    $("#normalizerIdCheck"+id[count]).val(normalizerIdBulkChange);
                }
            }
             $( "#dialog-form" ).dialog( "close" );
        }
    </script>
</html>
