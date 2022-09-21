<%-- 
    Document   : MyAppraisal
    Created on : Nov 11, 2010, 11:34:34 AM
    Author     : Administrator

--%>
<%--<%@include file="../../../../../header.jsp" %>--%>
<%@include file="../../qpd/qpddateinformation.jsp" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Annual Appraisal Form</title>
        <c:if test="${appraiseeDetails.submitStatus>=2}">
            <c:set var="readonlystatus" value="readonly"></c:set>
        </c:if>
        <script type="text/javascript">
            var fCount=0;
            function validateComments(){
                
                var errorFlag = 0;
                var scrollFlag = 0;
                var countKraData = 0;
                var countKraData2 = 0;

                var countDevelData = 0;
                var countDevelData2 = 0;

                var countGoalData = 0;
                var countGoalData2 = 0;

                var topAchievementCount=$("#topAchievementsCount").val()+1;
                var develNeedCount=$("#developmentNeedsCount").val()+1;
                var goalDataCount=$("#goalSheetCount").val()+1;
                            
                $(".kraData").each(function(){
                    
                                if($(this).parent().parent().find(".statusCheck").val()=='deleted'){
                                    countKraData++;
                                }
                                
                                if(($.trim(this.value)=="") && $(this).parent().parent().find(".statusCheck").val()=='undeleted'){
                                    countKraData2++;
                                }
                                
                });
                
                $(".develNeed").each(function(){

                                if($(this).parent().parent().find(".statusCheck").val()=='deleted'){
                                    countDevelData++;
                                }

                                if(($.trim(this.value)=="") && $(this).parent().parent().find(".statusCheck").val()=='undeleted'){
                                    countDevelData2++;
                                }

                });

                $(".goalData").each(function(){

                                if($(this).parent().parent().find(".statusCheck").val()=='deleted'){
                                    countGoalData++;
                                }

                                if(($.trim(this.value)=="") && $(this).parent().parent().find(".statusCheck").val()=='undeleted'){
                                    countGoalData2++;
                                }

                });
                            
                    if(parseInt(topAchievementCount,10) == parseInt(countKraData,10)){
                        if(scrollFlag==0){$.scrollTo("#topAchievements",{speed:1000,easing:'swing'});}
                            $("#customErrorTA").show();
                            $("#customErrorTA").html("<font color='red'>Enter Atleast One Achievement</font>");
                            scrollFlag++;
                            errorFlag++;
                        }else if(countKraData2>0){
                            if(scrollFlag==0){$.scrollTo("#topAchievements",{speed:1000,easing:'swing'});}
                            $("#customErrorTA").show();
                            $("#customErrorTA").html("<font color='red'>Enter Key Achievement</font>");
                            scrollFlag++;
                            errorFlag++;
                        }else{
                            $("#customErrorTA").hide();
                            $("#customErrorTA").html("");
                        }
                        
                    if(parseInt(develNeedCount,10) == parseInt(countDevelData,10)){
                        if(scrollFlag==0){$.scrollTo("#developmentNeeds",{speed:1000,easing:'swing'});}
                            $("#customErrorDN").show();
                            $("#customErrorDN").html("<font color='red'>Enter Atleast One Development Need</font>");
                            scrollFlag++;
                            errorFlag++;
                        }else if(countDevelData2>0){
                            if(scrollFlag==0){$.scrollTo("#developmentNeeds",{speed:1000,easing:'swing'});}
                            $("#customErrorDN").show();
                            $("#customErrorDN").html("<font color='red'>Enter Development Need</font>");
                            scrollFlag++;
                            errorFlag++;
                        }else{
                            $("#customErrorDN").hide();
                            $("#customErrorDN").html("");
                        }
                        
                    if(parseInt(goalDataCount,10) == parseInt(countGoalData,10)){
                        if(scrollFlag==0){$.scrollTo("#goalSheet",{speed:1000,easing:'swing'});}
                            $("#customErrorGD").show();
                            $("#customErrorGD").html("<font color='red'>Enter Atleast One Goal</font>");
                            scrollFlag++;
                            errorFlag++;
                        }else if(countGoalData2>0){
                            if(scrollFlag==0){$.scrollTo("#goalSheet",{speed:1000,easing:'swing'});}
                            $("#customErrorGD").show();
                            $("#customErrorGD").html("<font color='red'>Enter Goal</font>");
                            scrollFlag++;
                            errorFlag++;
                        }else{
                            $("#customErrorGD").hide();
                            $("#customErrorGD").html("");
                        }


                $(".customRequired").each(
                function(){
                    //var divElement = document.createElement("div");
                    //divElement.setAttribute("class","customError");
                    
                    if(this.value==""){
                        
                        //if(this.parentNode.parentNode.childNodes[1].className =='customError'){
                            if(scrollFlag==0){$.scrollTo(this,{speed:1000,easing:'swing'});}
                            //this.parentNode.parentNode.childNodes[1].innerHTML = "<font color='red'>Required data Missing</font>";
                            $(this).parent().parent().find(".customError").html("<font color='red'>Required Data Missing</font>");
                            scrollFlag++;
                        //}
                        errorFlag++;
                    }else{
                        //    divElement.innerHTML="<font color='red'>Required data Missing</font>";
                            $(this).parent().parent().find(".customError").html("");

                    }
                }
            )

                if(ValidateFiles()){

                }else{
                    errorFlag++;
                }
                
                if(errorFlag==0){
                    return true;
                }else{
                    return false;
                }
            }

    $("#appraiseeForm").submit(function() {
      $(".buttons").each(
            function(){$(this).hide();}
        );
      return true;
    });
            
            function removeErrorDiv(elementFocused){
                //if(elementFocused.value!="" && elementFocused.parentNode.parentNode.childNodes[3].className =='customError'){
                if(elementFocused.value!=""){
                    $(elementFocused).parent().parent().find(".customError").html("");
                    //elementFocused.parentNode.parentNode.childNodes[3].innerHTML = "";
                }else{
                    //elementFocused.parentNode.parentNode.childNodes[3].innerHTML = "<font color='red'>Required data Missing</font>";
                    $(elementFocused).parent().parent().find(".customError").html("<font color='red'>Required Data Missing</font>");
                }
            }
            
            function removeFileUpload(count){
                <%--$("#attachment"+count).remove();--%>
                <%--$("#remove"+count).remove();--%>
                $("#remove"+count).parent().remove();
            }
            
            function addFileElement(){
                var row = document.createElement('div');
                row.setAttribute("class", "attachedRow");

                var d = document.createElement('span');

if (/MSIE 7/.test(navigator.userAgent)){ //test for MSIE x.x;
            var file = document.createElement("<input type='file' name='attachment["+fCount+"]' id='attachment"+fCount+"' class='required fileUpload' onkeydown='this.blur()' >");
            row.appendChild(file);
}else{
                var file = document.createElement("input");
                file.setAttribute("type", "file");
//                file.setAttribute("onchange", "showRemoveLink("+fCount+")");
                file.setAttribute("name", "attachment["+fCount+"]");
                file.setAttribute("id", "attachment"+fCount+"");
                file.setAttribute("class", "required fileUpload");
                file.setAttribute("onkeydown", "this.blur()");
                row.appendChild(file);
}
                //var c = document.createElement('span');
                //var file1 = document.createElement("a");
                //file1.setAttribute("href", "javascript:void(0)");
                //file1.setAttribute("id", "remove"+fCount+"");
//              file1.setAttribute("style", "visibility : hidden");
                //file1.setAttribute("onClick","removeFileUpload("+fCount+")");
    
                //file1.innerHTML="Remove";
                //row.appendChild(file1);
                row.innerHTML += "<a href='javascript:void(0)' id='remove"+fCount+"' onclick='removeFileUpload("+fCount+")'>Remove</a>";
                document.getElementById("moreUploads").appendChild(row);
                //document.getElementById("moreUploads").appendChild(c);
                //document.getElementById("remove").innerHTML='<input type="text">';
                fCount++;
            }
            
            function showRemoveLink(removeLinkId){
                document.getElementById('remove'+removeLinkId+'').style.visibility="visible";
            }

            function fetchAppraiseeInfo(){
                document.getElementById('appraiseeHeader').action="${pageContext.request.contextPath}/com/defiance/ideal/qpd/appraisee/begin.do";
                document.getElementById('appraiseeHeader').submit();
            }



            function ValidateFiles(){
                
                var fileErrFlag = 0;
                var fileName;
                var ext;
                $(".fileUpload").each(
                    function(){
                        fileName = $(this).val();
                        ext = fileName.substring(fileName.lastIndexOf('.')+1);
                            
                       if(fileName==""){
                               $(this).parent().append("<div id=errorElement><font color=red>Attach file or click the remove button and click save/submit</font></div>");
                       }else{
                           if($(this).parent().find("#errorElement")){
                               $(this).parent().find("#errorElement").html("");
                           }
                       }
                       if((ext == "jpg" || ext == "png" || ext == "bmp" || ext == "rar" || ext == "zip" || ext == "msg" || ext == "txt" || ext == "docx" || ext == "doc" || ext == "xlsx" || ext == "XLSX" || ext == "xls" || ext == "pdf" || ext == "PDF" || ext == "XLS" || ext == "DOC" || ext == "DOCX" || ext == "TXT")){
                           if($(this).parent().find("#errorElement")){
                            $(this).parent().find("#errorElement").html("");
                           }
                       }else{
                            $(this).parent().append("<div id=errorElement><font color=red>File Type Not Allowed</font></div>");
                            fileErrFlag++;
                       }
                    }
                );

                    if(fileErrFlag==0){
                        return true;
                    }else{
                        return false;
                    }
            }
        </script>
    </head>
    <body>
        <div class="submenuwrap">
            <c:set var="dateExpired" value="notexpired"></c:set>
            <%@include file="../../../../../menu.jsp" %>
            <c:if test="${!empty(savedmsg)}">
                <div class="savedmsgalert"><center><font color="green">${savedmsg}</font></center></div>
            </c:if>
            <div class="contentwrap">
                <%--<div class="contentBand">Quarterly Performance Dialogue Form</div>--%>
                <div class="contentBand">Annual Appraisal Form</div>
                    <div class="contentarea">
                        <form action="${pageContext.request.contextPath}/com/defiance/ideal/qpd/appraisee/begin.do" method="post" name="appraiseeHeader" id="appraiseeHeader" >
                        <table width="100%">
                            <tr>
                             
                                <td><label class="headLabel">Employee Name</label></td><td><span class="displayText">${appraiseeDetails.appraiseeName}

                                         <c:if test="${appraiseeDetails.appraiseeName==null}">NIL</c:if></span></td>
                                        <%--${appraiseeDetails.appraiseeName}</span></td>--%>
                                <td><label class="headLabel">Employee ID</label></td><td><span class="displayText">${appraiseeEmpId}</span></td>
                                <%--<td><label class="headLabel">Quarter Under Review</label></td>--%>
                                <td><label class="headLabel">Year Under Review</label></td>
                                <td><span class="displayText">
                                        <%--<select name="appraisalQuarter" id="appraisalPeriod" class="required form2" style="width: 4em" onchange="fetchAppraiseeInfo()">

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
                                        </select>--%>
                                        <select name="appraisalYear" id="appraisalYear" class="required form2" style="width: 5em" onchange="fetchAppraiseeInfo()">

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
                                    </span>
                                    <!--                         <input class="buttons submitbutton" type="submit" value="Fetch" style="width: 5em" name="button" ></td>-->
                            </tr>
                            <tr>

                                <td><label class="headLabel">SBU</label></td><td><span class="displayText">${sbuName.appraiseeSBU} <c:if test="${sbuName.appraiseeSBU==null}">NIL</c:if></span></td>
                                <td><label class="headLabel">Appraiser</label></td><td><span class="displayText">${appraiseeDetails.appraiserName}<c:if test="${appraiseeDetails.appraiserName==null}">NIL</c:if></span></td>
                                <td><label class="headLabel">Reviewer</label></td><td><span class="displayText">${appraiseeDetails.reviewerName}<c:if test="${appraiseeDetails.reviewerName==null}">NIL</c:if></span></td>
                            </tr>
                            <tr>
                                <td><label class="headLabel">Date Of Joining</label></td><td><span class="displayText">${appraiseeDetails.joinedDate}<c:if test="${appraiseeDetails.joinedDate==null}">NIL</c:if></span></td>
                            </tr>
                        </table>
                    </form>
                </div>
                <c:if test="${appraiseeFinalDate <= currentDate && appraiseeDetails.submitStatus<2}">
<!--                    <div align="center"><font color="red" face="calibiri" size="3"><c:out value="Annual Appraisal Form Expired You Cannot Fill The Comments"></c:out></font></div>-->
                    <%--<c:set var="dateExpired" value="expired"></c:set>
                    <c:set var="readonlystatus" value="disabled"></c:set>--%>
                </c:if>
                <c:if test="${fn:length(kraData)==0}">
                    <div class="formarea">
                        <table width="100%" align="center">
                            <tr class="formarea_header" style="text-align: center">
                                <td style="text-align: center">
                                    -- Annual Appraisal Data Not Available For Year ${appraisalYear} --
                                </td>
                            </tr>
                        </table>
                    </div>
                </c:if>
                <c:if test="${fn:length(kraData)!=0}">

                    <div class="formarea">
                        <form action="${pageContext.request.contextPath}/com/defiance/ideal/qpd/appraisee/save.do" method="post" name="appraiseeForm" id="appraiseeForm" enctype="multipart/form-data">


                            <div>
                                <fieldset>
                                    <legend>Top achievements during the assessment period</legend>
                                    <div id="customErrorTA" style="text-align: center;border: 2px solid #dd0000;display: none"></div>
                                    <table id="topAchievements" width="100%" align="center" border="0" style="border-collapse: collapse">
                                        <tr>
                                            <th>
                                                Top Achievements During The Assessment Year
                                            </th>
                                            <%--<th>
                                                Employee remarks
                                            </th>--%>
                                            <c:if test="${appraiseeDetails.submitStatus<2}">
                                                <th width="100px">
                                                    Actions
                                                </th>
                                            </c:if>
                                        </tr
                                        <c:if test="${empty(topAchievements)}">
                                        <tr>
                                            <td class="tableData"><textarea ${readonlystatus} cols="" rows="" class="bigText kraData" id="keyResultAreas0" name="keyResultAreas[]"></textarea></td>
                                            <%--<td class="tableData"><textarea ${readonlystatus} cols="" rows="" class="bigText" id="employeeRemarks0" name="employeeRemarks[]"></textarea></td>--%>
                                            <c:if test="${appraiseeDetails.submitStatus<2}">
                                            <td class="tableData" style="text-align: center">
                                              <input type="hidden" name="topAchievementsId[]" id="topAchievementsId0" value="" />
                                              <input type="hidden" class="statusCheck" id="topAchievementsStatus0" name="topAchievementsStatus[]" value="undeleted" />
                                              <a href="javascript:void(0)" onclick="removeRow(this,0,'topAchievements')">Remove</a>
                                            </td>
                                            </c:if>
                                        </tr>
                                        </c:if>
                                        <c:forEach items="${topAchievements}" var="topAchievementsDetail" varStatus="taindex">
                                             <tr>
                                                <td class="tableData"><textarea ${readonlystatus} cols="" rows="" class="bigText kraData" id="keyResultAreas${taindex.index}" name="keyResultAreas[]">${topAchievementsDetail.keyResultArea}</textarea></td>
                                                <%--<td class="tableData"><textarea ${readonlystatus} cols="" rows="" class="bigText" id="employeeRemarks${taindex.index}" name="employeeRemarks[]">${topAchievementsDetail.employeeRemark}</textarea></td>--%>
                                                <c:if test="${appraiseeDetails.submitStatus<2}">
                                                <td class="tableData" style="text-align: center">
                                                <input type="hidden" name="topAchievementsId[]" id="topAchievementsId${taindex.index}" value="${topAchievementsDetail.achievementId}" />
                                                <input type="hidden" class="statusCheck" id="topAchievementsStatus${taindex.index}" name="topAchievementsStatus[]" value="undeleted" />
                                                <a href="javascript:void(0)" onclick="removeRow(this,${taindex.index},'topAchievements')">Remove</a>
                                                </td>
                                                </c:if>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    <c:if test="${appraiseeDetails.submitStatus<2}">
                                    <input type="button" class="buttons addbutton" onclick="addRow('topAchievements','topAchievementsId')" value="Add achievements" />
                                    <input type="hidden" id="topAchievementsCount" name="topAchievementsCount" value="" />
                                    </c:if>
                                </fieldset>
                            </div>
                            <br>
                            <br>
                            <input name="qpdId" type="hidden" value="${appraiseeDetails.qpdId}" />
                            <table width="100%" border="0">
                                <thead>
                                    <tr class="formarea_header">
                                        <th width="200px;"></th>
                                        <th>Performance Indicator</th>
                                        <%--<th>MEASUREMENT CRITERIA</th>--%>
                                        <th>Weightage</th>
                                        <th>Key Achievements - Comments</th>
                                        <c:if test="${appraiseeDetails.submitStatus>=4}">
                                                <th>Appraiser Comments</th>
                                        </c:if>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set value="0" var="rowDisplay"></c:set>
                                    <c:set value="0" var="KraIdCount"></c:set>
                                    <c:forEach items="${kraData}" var="datum" varStatus="item">
                                        <c:choose>
                                            <c:when test="${(item.index+1)%2==0}">
                                                <c:set value="formarea_row2" var="rowDisplay"></c:set>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set value="formarea_row1" var="rowDisplay"></c:set>
                                            </c:otherwise>
                                        </c:choose>
                                        <tr class="${rowDisplay}">
                                            <td>
                                                <c:choose>
                                                    <c:when test="${item.index>0}">
                                                        <c:choose>
                                                            <c:when test="${kraData[item.index].kraDescription!=kraData[item.index-1].kraDescription}">
                                                                ${kraData[item.index].kraDescription}
                                                            </c:when>
                                                            <c:otherwise>

                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${kraData[item.index].kraDescription}
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:if test="${datum.qpdKraId==0}">
                                                    <c:set value="${KraIdCount+1}" var="KraIdCount"></c:set>
                                                </c:if>
                                            </td>
                                            <%--<td>
                                                <input type="hidden" name="qpdKraIdHidden[]" id="qpdKraIdHidden" value="${datum.qpdKraId}">
                                                <input type="hidden" name="kraQtrIdHidden[]" id="kraQtrIdHidden" value="${datum.kraQtrId}">
                                                <input type="hidden" name="appraiseeYear" value="${appraisalYear}" id="appraiseeYear">
                                                <input type="hidden" name="appraiseeQuarter" value="${appraisalQuarter}" id="appraiseeQuarter">
                                                ${datum.kraDescription}</td>--%>
                                            <td>
                                                <input type="hidden" name="qpdKraIdHidden[]" id="qpdKraIdHidden" value="${datum.qpdKraId}">
                                                <input type="hidden" name="kraQtrIdHidden[]" id="kraQtrIdHidden" value="${datum.kraQtrId}">
                                                <input type="hidden" name="appraiseeYear" value="${appraisalYear}" id="appraiseeYear">
                                                <input type="hidden" name="appraiseeQuarter" value="${appraisalQuarter}" id="appraiseeQuarter">
                                                <b>${datum.attributes}</b>&nbsp;<span class="displayText">${datum.performanceCriteria}</span></td>
                                            <%--<td><span class="displayText">${datum.measurementCriteria}</span></td>--%>
                                            <td style="text-align: center"><span class="displayText">${datum.weightage}%</span></td>
                                            <td>
                                                <textarea class="customRequired resizableTextArea" cols="15" rows="3" name="appraiseeComments" id="appraiseeComments${datum.kraQtrId}" ${readonlystatus} onchange="removeErrorDiv(this)">${datum.selfComments}</textarea>
                                                <div class="customError"></div>
                                            </td>
                                            <c:if test="${appraiseeDetails.submitStatus>=4}">
                                                <td class="commentText">${datum.appraiserCommentsNew}</td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                            </table>

                            <div align="center">
                                <c:if test="${!empty(fileErrMsg)}">
                                    <div class="savedmsgalert"><center><font color="red">${fileErrMsg}</font></center></div>
                                </c:if>
                                            <c:if test="${appraiseeDetails.submitStatus<2}"><a href="javascript:void(0)" onclick="addFileElement();">Attach Document</a>
                                                <div id="moreUploads"></div>
                                            </c:if>
                                            <c:forEach items="${fileDetails}" var="file" varStatus="index">
                                                <c:set var="fileNameArray" value="${fn:split(file.fileName,'~~')}"></c:set>
                                                <a href="javascript:void(0)" onclick="fileDownload('${file.fileName}','${file.fileType}','${file.referenceName}','${file.referenceId}','${file.fileId}','${file.moduleName}')">${fileNameArray[2]}</a>
                                                <c:if test="${appraiseeDetails.submitStatus<2}"><span>||</span><a href="javascript:void(0)" onclick="fileRemove('${file.fileName}','${file.fileType}','${file.referenceName}','${file.referenceId}','${file.fileId}','${file.moduleName}','${fileNameArray[2]}')">Remove attachment</a></c:if><br>
                                            </c:forEach>
                            </div>


                            <div>
                                <fieldset>
                                    <legend>Development Needs (Add only one development need per row)</legend>
                                    <div id="customErrorDN" style="text-align: center;border: 2px solid #dd0000;display: none"></div>
                                    <table id="developmentNeeds" width="100%" align="center" border="0" style="border-collapse: collapse">
                                        <tr>
                                            <th>
                                                Development Needs
                                            </th>
                                            <c:if test="${appraiseeDetails.submitStatus<2}">
                                            <th width="100px">
                                                Actions
                                            </th>
                                            </c:if>
                                        </tr>
                                        <c:if test="${empty(developmentNeeds)}">
                                        <tr>
                                            <td class="tableData"><textarea ${readonlystatus} cols="" rows="" class="bigText develNeed" id="devNeedData0" name="devNeedData[]"></textarea></td>
                                            <c:if test="${appraiseeDetails.submitStatus<2}">
                                            <td class="tableData" style="text-align: center">
                                                <input type="hidden" name="developmentNeedsId[]" id="developmentNeedsId0" value="" />
                                                <input type="hidden" class="statusCheck" id="developmentNeedsStatus0" name="developmentNeedsStatus[]" value="undeleted" />
                                                <a href="javascript:void(0)" onclick="removeRow(this,0,'developmentNeeds')">Remove</a>
                                            </td>
                                            </c:if>
                                        </tr>
                                        </c:if>
                                        <c:forEach items="${developmentNeeds}" var="developmentNeedsDetail" varStatus="dnindex">
                                             <tr>
                                                <td class="tableData"><textarea ${readonlystatus} cols="" rows="" class="bigText develNeed" id="devNeedData${dnindex.index}" name="devNeedData[]">${developmentNeedsDetail.developmentNeed}</textarea></td>
                                                <c:if test="${appraiseeDetails.submitStatus<2}">
                                                <td class="tableData" style="text-align: center">
                                                    <input type="hidden" name="developmentNeedsId[]" id="developmentNeedsId${dnindex.index}" value="${developmentNeedsDetail.needsId}" />
                                                    <input type="hidden" class="statusCheck" id="developmentNeedsStatus${dnindex.index}" name="developmentNeedsStatus[]" value="undeleted" />
                                                    <a href="javascript:void(0)" onclick="removeRow(this,${dnindex.index},'developmentNeeds')">Remove</a>
                                                </td>
                                                </c:if>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    <c:if test="${appraiseeDetails.submitStatus<2}">
                                    <input type="button" class="buttons addbutton" onclick="addRow('developmentNeeds','developmentNeedsId')" value="Add Development needs" />
                                    <input type="hidden" id="developmentNeedsCount" name="developmentNeedsCount" value="" />
                                    </c:if>
                                </fieldset>
                            </div>
                            <br>
                            <div>
                                <fieldset>
                                    <legend>Goal Sheet For 2011-2012 (Add only one goal per row)</legend>
                                    <div id="customErrorGD" style="text-align: center;border: 2px solid #dd0000;display: none"></div>
                                    <table id="goalSheet" width="100%" align="center" border="0" style="border-collapse: collapse">
                                        <tr>
                                            <th>
                                                Goals
                                            </th>
                                            <c:if test="${appraiseeDetails.submitStatus<2}">
                                            <th width="100px">
                                                Actions
                                            </th>
                                            </c:if>
                                        </tr
                                        <c:if test="${empty(goalSheet)}">
                                        <tr>
                                            <td class="tableData"><textarea ${readonlystatus} cols="" rows="" class="bigText goalData" id="goalData0" name="goalData[]"></textarea></td>
                                            <c:if test="${appraiseeDetails.submitStatus<2}">
                                            <td class="tableData" style="text-align: center">
                                                <input type="hidden" name="goalId[]" id="goalId0" value="" />
                                                <input type="hidden" class="statusCheck" id="goalSheetStatus0" name="goalSheetStatus[]" value="undeleted" />
                                                <a href="javascript:void(0)" onclick="removeRow(this,0,'goalSheet')">Remove</a>
                                            </td>
                                            </c:if>
                                        </tr>
                                        </c:if>
                                        <c:forEach items="${goalSheet}" var="goalSheetDetail" varStatus="glIndex">
                                             <tr>
                                                <td class="tableData"><textarea ${readonlystatus} cols="" rows="" class="bigText goalData" id="goalData${glIndex.index}" name="goalData[]">${goalSheetDetail.glData}</textarea></td>
                                                <c:if test="${appraiseeDetails.submitStatus<2}">
                                                <td class="tableData" style="text-align: center">
                                                    <input type="hidden" name="goalId[]" id="goalId${glIndex.index}" value="${goalSheetDetail.glId}" />
                                                    <input type="hidden" class="statusCheck" id="goalSheetStatus${glIndex.index}" name="goalSheetStatus[]" value="undeleted" />
                                                    <a href="javascript:void(0)" onclick="removeRow(this,${glIndex.index},'goalSheet')">Remove</a>
                                                </td>
                                                </c:if>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    <c:if test="${appraiseeDetails.submitStatus<2}">
                                    <input type="button" class="buttons addbutton" onclick="addRow('goalSheet','goalId')" value="Add Goals" />
                                    <input type="hidden" id="goalSheetCount" name="goalSheetCount" value="" />
                                    </c:if>
                                </fieldset>
                            </div>
                                    <br>

                            <table align="center">
                                    <tr>
                                        <td>
                                            <c:if test="${KraIdCount==0}">
                                            <input class="buttons exportbutton" value="Export to Excel" type="submit" />
                                            </c:if>
                                            <c:if test="${appraiseeDetails.submitStatus<2 && dateExpired=='notexpired'}">
                                                <input class="buttons savebutton" type="submit" value="Save" name="button" onclick="return validateComments()">
                                                <input class="buttons submitbutton"  style="width: 150px" type="submit" value="Submit for Review" name="button" onclick="return validateComments()" >
                                                <%--<input class="styledButton buttons resetbutton" value="Reset" type="reset" />--%>
                                            </c:if>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                        <form action="" method="post" name="fileDownloadForm" id="fileDownloadForm">
                            <input class="fileUplaodData" type="hidden" name="fileName" id="fileName" />
                            <input class="fileUplaodData" type="hidden" name="fileType" id="fileType" />
                            <input class="fileUplaodData" type="hidden" name="referenceName" id="referenceName" />
                            <input class="fileUplaodData" type="hidden" name="referenceId" id="referenceId" />
                            <input class="fileUplaodData" type="hidden" name="fileId" id="fileId" />
                            <input class="fileUplaodData" type="hidden" name="moduleName" id="moduleName" />
                        </form>
<c:if test="${appraiseeDetails.submitStatus>=4}">
                            <table width="100%">
                                    <tr>
                                        <td>
                                            Appraiser Overall Comments -
                                        </td>
                                        <td class="commentText">
                                            ${appraiseeDetails.appraiserComments}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Areas Of Improvement -
                                        </td>
                                        <td class="commentText">
                                            ${appraiseeDetails.areasOfImprovement}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Technology Training Recommended By Appraiser -
                                        </td>
                                        <td class="commentText">
                                            ${appraiseeDetails.technologyTraining}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="300px">
                                            Soft Skill Training Recommended By Appraiser -
                                        </td>
                                        <td class="commentText">
                                            ${appraiseeDetails.softskillTraining}
                                        </td>
                                    </tr>
                            </table>
</c:if>

                            


                            <table align="center">
                                    <tr>
                                        <td>
                                            <c:if test="${appraiseeDetails.submitStatus>=9}">
                                                <span class="displayText"><b>Annual Appraisal Ratings For ${appraisalYear} -</b></span> <font size="3">${appraiseeDetails.finalRating}</font>
                                            </c:if>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                    </div>
                </div>
            </c:if>
        </div>
    </body>
      <script type="text/javascript">
            function fileDownload(fileName,fileType,referenceName,referenceId,fileId,moduleName){
                $("#fileName").val(fileName);
                $("#fileType").val(fileType);
                $("#referenceName").val(referenceName);
                $("#referenceId").val(referenceId);
                $("#fileId").val(fileId);
                $("#moduleName").val(moduleName);
                document.getElementById('fileDownloadForm').action='fileDownload.do';
                document.getElementById('fileDownloadForm').submit();
                //$('#fileDownloadForm').submit();
            }
            function fileRemove(fileName,fileType,referenceName,referenceId,fileId,moduleName,displayName){
                //var confirmStatus=confirm("Are you sure you want to remove \""+displayName+"\" ?");
                $("#fileName").val(fileName);
                $("#fileType").val(fileType);
                $("#referenceName").val(referenceName);
                $("#referenceId").val(referenceId);
                $("#fileId").val(fileId);
                $("#moduleName").val(moduleName);
                document.getElementById('fileDownloadForm').action='fileRemove.do';
                document.getElementById('fileDownloadForm').submit();
            }
            $(document).ready(function(){
                 $(".resizableTextArea").resizable();
                 $('#topAchievementsCount').val($("#topAchievements tr").length-2);
                 $('#developmentNeedsCount').val($("#developmentNeeds tr").length-2);
                 $('#goalSheetCount').val($("#goalSheet tr").length-2);
            });

            function addRow(tableName,hiddenMasterId){
                
                var noOfRows = $("#"+tableName+" tr").length - 1;
                    if(tableName == 'topAchievements'){
                        dataToAdd = getAchievementsData(noOfRows);
                    }else if(tableName == 'developmentNeeds'){
                        dataToAdd = getDnData(noOfRows);
                    }else if(tableName == 'goalSheet'){
                        dataToAdd = getGoalData(noOfRows);
                    }
                $("#"+tableName+" tr:last").after('<tr>'+dataToAdd+'<td class="tableData" style="text-align:center"><input type="hidden" name="'+hiddenMasterId+'[]" id="'+hiddenMasterId+noOfRows+'" value="" /><input type="hidden" class="statusCheck" id="'+tableName+'Status'+noOfRows+'" name="'+tableName+'Status[]" /><a href="javascript:void(0)" onclick="removeRow(this,'+noOfRows+',\''+tableName+'\')">Remove</a></td></tr>');
                $('#'+tableName+'Status'+noOfRows).val("undeleted");
                $("#"+tableName+"Count").val(noOfRows);
                
            }

            function getAchievementsData(rowId){
                //var dataToAdd = '<td class="tableData"><textarea class="bigText" id="keyResultAreas'+rowId+'" name="keyResultAreas[]"></textarea></td><td class="tableData"><textarea class="bigText" id="keyResultAreas'+rowId+'" name="employeeRemarks[]"></textarea></td>';
                var dataToAdd = '<td class="tableData"><textarea class="bigText kraData" id="keyResultAreas'+rowId+'" name="keyResultAreas[]"></textarea></td>';
                return dataToAdd;
            }
            function getDnData(rowId){
                var dataToAdd = '<td class="tableData"><textarea cols="" rows="" class="bigText develNeed" id="devNeedData'+rowId+'" name="devNeedData[]"></textarea></td>';
                return dataToAdd;
            }

            function getGoalData(rowId){
                var dataToAdd = '<td class="tableData"><textarea cols="" rows="" class="bigText goalData" id="goalData'+rowId+'" name="goalData[]"></textarea></td>';
                return dataToAdd;
            }

            function removeRow(rowToRemove,rowNo,tableName){
              $("#"+tableName+"Status"+rowNo).val("deleted");
              $(rowToRemove).parent().parent().attr("class","hidden");
            }

    </script>
    <style type="text/css">
        .commentText{
            font-style: normal;
            font-weight: lighter;
            font-family:sans-serif, fantasy;
            text-align: left;
        }
        .bigText{
        width: 95%;
        height:30px;
        }
        .hidden{
            display: none;
        }
        .tableData{
            
        }
    </style>
</html>

