<%@include file="header.jsp" %>

<head>
    <title>New Request</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>
</head>
<body>
    <%
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String date = formatter.format(new java.util.Date());
    %>
    <div class="goToList">
        <img alt="Requestor List" title="Requestor List" src="/iDeal_application/css/images/icon_list.png"/>
        <a  style="text-decoration:none;color: #4C83B3;" target="_self" href="qualityCustomerList.htm">Requestor List</a>
    </div>
    <div id="container">

        <!--        <div style="margin: 5px;">
                    <a class="list" style="text-decoration:none;" target="_self" href="qualityCustomerList.htm">Requestor List</a>
                </div>-->
        <div id="">
            <div class="container_inner">
                <div class="page_heading">
                    New Request 
                </div></div>
            <div class="notice_page">
                <div style="float: left"><img src="/ideal_rrf_new/css/images/icon_alert.png" title="Information" alt="Information"></img></div>
                <div style="padding-left: 10px;padding-top: 1px;">
                    <img src="/ideal_rrf_new/css/images/tick.png" title="Information" alt="Information" style="margin-left: 15px;margin-right: 10px;"></img>
                    Fields marked in * are mandatory.
                </div>
            </div>
            <!--            <p class="paging_details"><span class="heading">New Request</span> </p>-->

            <form action="#" id="newRequest" name="newRequest" method="POST" enctype="multipart/form-data">
                <div id="datadisplay" class="formContent_new" style="height:auto">
                    <div id="outer" class="data">
                        <div id="queryComp">
                            <table border="0" width="50%" >
                                <tr>
                                    <td align="center" colspan="2" width="100%" height="15">
                                        <div id="errormessage" class="error-message">${Result}</div>
                                    </td>
                                </tr>
                                <tr >
                                    <td><label >Requestor Name </label></td>
                                    <td>${empDetails.employeeId}   ${empDetails.employeeName}</td>
                                </tr>

                                <tr>

                                    <td><input type="hidden"  id="empLocation" name="empLocation" readonly value="${empDetails.empLocation}"/></td>
                                </tr>
                                <tr>

                                    <td><input type="hidden"  id="reqDate" name="reqDate" readonly value="<%= date%>"/></td>
                                </tr>
                                <tr >
                                    <td><label>FeedBack Type <font color="red">*</font> </label></td>
                                    <td><select id="feedbackType" name="feedbackType" class="required textbox_new" onchange="changefeedback();">
                                            <option value="">-- Select Feedback-- </option>
                                            <c:forEach items="${feedbackType}" var="feedbackType" varStatus="i">
                                                <option value="${feedbackType.configKey}">${feedbackType.configValue}</option>
                                            </c:forEach>
                                        </select></td>
                                </tr>
                                <tr >
                                    <td><label>Description <font color="red">*</font> </label></td>
                                    <td><textarea id="description" class=" required textbox_new" name="description" style="width: 400px;height: 90px;"></textarea></td>
                                </tr>
                                <tr >
                                    <td><label>Attach File</label></td>
                                    <td><input type="file" id="fileName" class="" name="fileName" value="fileName"/></td>
                                </tr>
                            </table>
                        </div>
                        <div id="processImp" style="display: none;">
                            <table border="0" width="50%"  >
                                <tr >
                                    <td><label>Improvement Request Type <font color="red">*</font> </label></td>
                                    <td><select id="improvementRequest" name="improvementRequest" class=" required textbox_new">
                                            <option value="">--Select Improvement Request Type- </option>
                                            <c:forEach items="${improvementType}" var="improvementType" varStatus="i">
                                                <option value="${improvementType.configKey}">${improvementType.configValue}</option>
                                            </c:forEach>
                                        </select></td>
                                </tr>
                                <tr >
                                    <td><label>Process Area <font color="red">*</font></label></td>
                                    <td><select id="processArea" name="processArea" class=" required textbox_new" onchange="getFocusArea(this.value);"  >
                                            <option value="">-- Select Process Area-- </option>
                                            <c:forEach items="${processType}" var="processType" varStatus="i">
                                                <option value="${processType.parentId}">${processType.configValue}</option>
                                            </c:forEach>
                                        </select></td>
                                </tr>
                                <tr >
                                    <td><label>Focus Area <font color="red">*</font></label></td>
                                    <td><select id="focusArea" class=" required textbox_new" name="focusArea">
                                            <option value="0">-- Select Focus Area-- </option>

                                        </select></td>
                                </tr>
                                <tr > 
                                    <td><label>Justification <font color="red">*</font></label></td>
                                    <td class="justi"><textarea id="justification" class=" required textbox_new" name="justification" style="width: 400px;height: 90px;"></textarea></td>
                                </tr>
                            </table>
                        </div>
                    </div>


                    <div class="buttonAlignment">
                        <div id="btnGroup" class="buttonAlignment" >
                            <!--                                        <div id="actionBtn" style="padding-left: 200px;">-->
                            <input type="hidden" id="referenceId" name="referenceId" readonly value=""/>
                            <input type="hidden" id="employeeId" name="employeeId" readonly value="${employeeId}"/>
                            <input type="hidden" id="empCurrentLocation" name="empCurrentLocation" readonly value="${empDetails.empCurrentLocation}"/>
                            <input type="hidden" id="list" name="list" readonly value="${listType}"/>
                            <input type="hidden" id="empId" name="empId" readonly value="${empDetails.employeeName}  ${empDetails.employeeId}" />
                            <button class=" qualitysubmit" name="submitButton" id="submitButton" value="1"  onclick="submitAction('addNewRequest.htm');">Submit</button>
                            <input class=" qualitycancel" type="button" id="cancelBtn" name="cancelBtn"  value="Cancel" onclick="javascript: location.href='authenticate.htm?empId=${employee_no}&modId=569' " />
                            <!--                            <button class=" qualCancel" name="cancelBtn" id="cancelBtn" style="width: 75px;background-color: #DFE8F6;" onclick="cancelAction();" >Cancel</button>-->

                            <!--                        </div>-->
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script type="text/javascript">
        
        function submitAction(formAction)
        {
           
            $("#newRequest").validate();
            var feedbackType=document.getElementById("feedbackType").value;
            if(feedbackType=='p' || feedbackType=='')
            {
                if($("#newRequest").valid()) {
                    document.getElementById("submitButton").style.visibility = 'hidden';
                    document.getElementById("cancelBtn").style.visibility = 'hidden';
                    document.newRequest.action=formAction;
                    document.newRequest.submit();
                }
            }
            else
            {
                var justification = document.getElementById('justification');
                var improventRequest = document.getElementById('improvementRequest');
                var processArea = document.getElementById('processArea');
                var focusArea = document.getElementById('focusArea');
                justification.setAttribute('class','');
                improventRequest.setAttribute('class','');
                processArea.setAttribute('class','');
                focusArea.setAttribute('class','');
                if($("#newRequest").valid()) {
                    document.getElementById("submitButton").style.visibility = 'hidden';
                    document.getElementById("cancelBtn").style.visibility = 'hidden';
                    document.newRequest.action=formAction;
                    document.newRequest.submit();
                }
            }
        }
        
      
        
        function getFocusArea(selectedValue) {
            if(selectedValue != 0){
                $('#focusArea').removeAttr('disabled');
                $.ajax({
                    url:"getFocusArea.htm?id="+selectedValue,
                    dataType: "html",
                    success:function(data){
                        $('#focusArea').html(data);
                    }
                });
            }
            else
            {
                $('#focusArea').attr("disabled","disabled");
            }
        }
        function changefeedback()
        {
            var feedbackType = document.getElementById("feedbackType").value;
            if(feedbackType=='p')
            {
                document.getElementById("processImp").style.display = 'block'; 
            }
            else
            {
                document.getElementById("processImp").style.display = 'none'; 
            }
        }
    </script>
</head>

</body>
