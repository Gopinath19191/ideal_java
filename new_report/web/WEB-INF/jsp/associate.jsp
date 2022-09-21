<%-- 
    Document   : associate
    Created on : Oct 13, 2011, 12:43:59 PM
    Author     : 14517
--%>
<%@include file="header.jsp" %>
<html>
    <head>
        <title>Associate Report</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/filterOnChange.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
    </head>
    <style type="text/css">
        .gobutton{
            border: 1px solid #BCCFEA;
            background: none repeat scroll 0 0 #316ca8;
            color: #FFFFFF;
            float: left;
            font-weight: bold;
            height: 32px;
            margin: 0px;
            width: 50px;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#joinedStartDate").datepicker({changeMonth: true, changeYear: true, disabled: true, dateFormat: 'dd-mm-yy', maxDate: new Date()});
            $("#joinedEndDate").datepicker({changeMonth: true, changeYear: true, disabled: true, dateFormat: 'dd-mm-yy', maxDate: new Date()});
            $("#activeEmpDate").datepicker({changeMonth: true, changeYear: true, disabled: true, dateFormat: 'dd-mm-yy', maxDate: new Date()});
            
        });

        function getExcelReport() {
            if(selectedEmpStatus() == false){
                 return false;
             }
            $('#associatereportPage').attr("action", "excelexportAssociate.htm");
            document.associatereportPage.submit();
        }
        function getFilterList() {
             if(selectedEmpStatus() == false){
                 return false;
             }
            $('#associatereportPage').attr("action", "associatelist.htm");
            var start_date = $('#joinedStartDate').val().split('-');
            var end_date = $('#joinedEndDate').val().split('-');
            if (start_date[2] > end_date[2]) {
                alert("From Date should be lesser than To Date.");
                return false;
            } else if (start_date[1] > end_date[1] && start_date[2] == end_date[2]) {
                alert("From Date should be lesser than To Date.");
                return false;
            } else if (start_date[0] > end_date[0] && start_date[1] == end_date[1] && start_date[2] == end_date[2]) {
                alert("From Date should be lesser than To Date.");
                return false;
            } else if ($('#joinedStartDate').val() != null && $('#joinedStartDate').val() != '') {
                if ($('#joinedEndDate').val() == null || $('#joinedEndDate').val() == '') {
                    alert("Please select End Date.");
                    return false;
                }
            } else if ($('#joinedEndDate').val() != null && $('#joinedEndDate').val() != '') {
                if ($('#joinedStartDate').val() == null || $('#joinedStartDate').val() == '') {
                    alert("Please select Start Date.");
                    return false;
                }
            } else {
                document.associatereportPage.submit();
                startLoading();
                return true;
            }
        }
        
        function loadForm(page) {
             if(selectedEmpStatus() == false){
                 return false;
             }
            $('#page').val(page);
            $('#associatereportPage').attr("action", "associatelist.htm");
            document.associatereportPage.submit();
        }

        function getSubSbu1(value)
        {
            //alert("MUNI");
            var subSbu;
            if (value == "DTS" || value == "5")
            {
                subSbu = "5";
            } else if (value == "PES" || value == "2")
            {
                subSbu = "2";
            }
            if (value == "HR & FACILITIES" || value == "13")
            {
                subSbu = "13";
            } else if (value == "Operations" || value == "15")
            {
                subSbu = "15";
            }
            if (value == "Finance" || value == "19")
            {
                subSbu = "19";
            } else if (value == "Corporate" || value == "21")
            {
                subSbu = "21";
            } else if (value == "Sales" || value == "23")
            {
                subSbu = "23";
            }
             else if (value == "Obsolete" || value == "43")
            {
                subSbu = "43";
            }
//    else if(value=="ERP" || value=="11")
//    {
//        subSbu="11";
//    }
            else if (value == "All" || value == '')
            {
                subSbu = "2,5,13,15,19,21,23,43";
            }
            $.ajax(
                    {
                        url: "getSubPracticeGroup.htm?id=" + value,
                        dataType: "html",
                        success: function(data) {
                            $('#subSbu').length = 0;
                            $('#subSbu').html(data);
                        }
                    });
        }

        function setSbu1(Value, id)
        {
            if (id == 'sbu') {
                if (Value == 3 || Value == 25 || Value == 30 || Value == 34){
                    $('#' + id).val("PES");
                }else if (Value == 6 || Value == 8 || Value == 9 || Value == 26){
                    $('#' + id).val("DTS");
                } else if (Value == 14 || Value == 17) {
                    $('#' + id).val("HR & FACILITIES");
                } else if (Value == 16 || Value == 18 || Value == 27 || Value == 35 || Value == 36 || Value == 37 || Value == 38) {
                    $('#' + id).val("Operations");
                } else if (Value == 20) {
                    $('#' + id).val("Finance");
                } else if (Value == 22 || Value == 39 || Value == 40 || Value == 41) {
                    $('#' + id).val("Corporate");
                } else if (Value == 24 || Value == 31 || Value == 32 || Value == 42) {
                    $('#' + id).val("Sales");
                } else if (Value == 44) {
                    $('#' + id).val("Obsolete");
                }
                 else {

                }
            }

        }
       
         function selectedEmpStatus(){
              var atLeastOneIsChecked = $('.checkboxArray :checkbox:checked').length > 0;
			if(atLeastOneIsChecked === false){
				alert("Please select atleast one employment status");
				return false;
			}else{
                            var selectempStatus = '';
                            $('.checkboxArray :checkbox:checked').each(
                              function(index,value){
                                   var checkBoxId = this.id;
                                   var rowValue =  checkBoxId.substring(2);
                                  var  key  =  $('#empStatusId'+rowValue).val();
                                  if(index == 0)
                                  selectempStatus += "'"+key+"'";
                                   else
                                  selectempStatus += ","+"'"+key+"'";


                              });
                              $('#status').val(selectempStatus);
                               return true;
                        }
        }
     


    </script>


    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:200%;background-color:rgba(0,0,0,0.5);display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">
            <div class="container_inner">
                <div class="page_heading">
                    Associate Reports
                </div>
            </div>

            <div class="tabletools">
                <form action="#" name="associatereportPage" method="post" id="associatereportPage">

                    <!--                    <input type="hidden" id="sub_sbu" name="sub_sbu" value=""/>-->
                    <table id="searchForm" width="100%">
                        <tbody>
                        <input type="hidden" id="page" name="page" value="${paging[0] > 1 ? paging[1]:'1'}" />
                        <tr style="height: 30px">
                            <td>
                                <label for="sbu" style="width:55px;"><b>BU</b></label>
                                <!--                                    <select name="sbu" id="sbu" class="selectbox textbox_new" style="width: 95px;" onchange="getSubSbu(this.value);">
                                <%--<c:if test="${result.sbu=='All'}">
                                        <c:set var="selAll" value="selected='selected'"></c:set>
                                </c:if>
                                <c:if test="${result.sbu=='Engineering'}">
                                        <c:set var="selEngg" value="selected='selected'"></c:set>
                                </c:if>
                                <c:if test="${result.sbu=='ERP'}">
                                        <c:set var="selErp" value="selected='selected'"></c:set>
                                </c:if>
                                <c:if test="${result.sbu=='IT'}">
                                        <c:set var="selIt" value="selected='selected'"></c:set>
                                </c:if>--%>
                                <option ${selAll} value="All">All</option>
                                <option ${selEngg} value="Engineering">Engineering</option>
                                <option ${selErp} value="ERP">ERP</option>
                                <option ${selIt} value="IT">IT</option>
                        </select>-->
<!--                                <select name="sbu" id="sbu" class="selectbox textbox_new" style="width: 175px;" onchange="getSubSbu1(this.value);">
                                    <c:if test="${result.sbu=='All'}">
                                        <c:set var="selAll" value="selected='selected'"></c:set>
                                    </c:if>
                                    <c:if test="${result.sbu=='PES'}">
                                        <c:set var="selEngg" value="selected='selected'"></c:set>
                                    </c:if>
                                    <c:if test="${result.sbu=='DTS'}">
                                        <c:set var="selIt" value="selected='selected'"></c:set>
                                    </c:if>

                                    <c:if test="${result.sbu=='HR & FACILITIES'}">
                                        <c:set var="selHr" value="selected='selected'"></c:set>
                                    </c:if>
                                    <c:if test="${result.sbu=='Operations'}">
                                        <c:set var="selOperations" value="selected='selected'"></c:set>
                                    </c:if>
                                    <c:if test="${result.sbu=='Finance'}">
                                        <c:set var="selFinance" value="selected='selected'"></c:set>
                                    </c:if>
                                    <c:if test="${result.sbu=='Corporate'}">
                                        <c:set var="selCorporate" value="selected='selected'"></c:set>
                                    </c:if>
                                    <c:if test="${result.sbu=='Sales'}">
                                        <c:set var="selSales" value="selected='selected'"></c:set>
                                    </c:if>
                                    <option ${selAll} value="All">All</option>
                                    <option ${selEngg} value="PES">PES</option>
                                    <option ${selIt} value="DTS">DTS</option>
                                    <option ${selHr} value="HR & FACILITIES">HR & FACILITIES</option>
                                    <option ${selOperations} value="Operations">Operations</option>
                                    <option ${selFinance} value="Finance">Finance</option>
                                    <option ${selCorporate} value="Corporate">Corporate</option>
                                    <option ${selSales} value="Sales">Sales</option>
                                </select>-->
                                <select name="sbu" id="sbu" class="selectbox textbox_new" style="width: 175px;" onchange="getSubSbu1(this.value);">
                                        <option  value="All">All</option>
                                        <c:forEach items="${sbu}" var="sbu" varStatus="i">
                                            <option  value="${sbu.id}" ${sbu.id eq result.sbu ? 'selected="selected"':''}>${sbu.name}</option>
                                        </c:forEach>
                                </select>
                            </td>

                            <c:if test="${result.sbu!=''}"> 
                                <td id="subPractice"  >
                                                              
                                    <label for="JoiningDate" style="width:210px;" ><b>Joining Date From :</b></label>

                                    <input type="text" id="joinedStartDate" name="joinedStartDate" value="${result.joinedStartDate}" style="width: 90px;"/>
                                </td>
                                <td>
                                     <label for="JoiningDate" ><b>Joining Date To :</b></label>
                                    <input type="text" id="joinedEndDate" name="joinedEndDate" value="${result.joinedEndDate}" style="width: 90px;"/>
                                </td>
                            </tr>
   
   
                            </c:if>

                        </tr>
                        <tr style="height: 30px">
                            <td>
                                <label for="subSbu" style="width:55px;"><b>Practice</b></label>
                                <select name="subSbu" id="subSbu" class="selectbox textbox_new"  style="width: 175px;" >
                                    <option  value="All">All</option>
                                    <c:forEach items="${subSbu}" var="subSbu" varStatus="i">
                                        <option  value="${subSbu.id}" ${subSbu.id eq result.subSbu ? 'selected="selected"':''}>${subSbu.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                               <label for="JoiningDate" style="width:210px;"><b>Exclude Associates relieved before:</b></label>
                                    <input type="text" id="activeEmpDate" name="activeEmpDate" value="${result.activeEmpDate}" style="width: 90px;"/>
                                </td>                           
                        </tr>
                        </tbody>
                    </table>
                                 <label style="width:190%;margin-left:3px;"><b>Employment Status :</b></label>
                   <div>
                      
                        <table>
                       <c:forEach items="${empStatus}" var="empStatus" varStatus="i">
                            <tr class="empTableStatus" style="width: 25%; float: left">
                                <td>
                                    <fieldset class = "checkboxArray" style="border: none; margin-top: 0px; padding: 0px;" >
                                        <input type="checkbox"   id="ch${i.index}">
                                    </fieldset>
                                </td> 
                                <td style="width: 90%;">
                                   <input type="hidden" id ="empStatusId${i.index}"  value="${empStatus.id}" >
                                   <label  style="width: 90%;">${empStatus.name}</label>
                                </td>          
                            </tr>
                       </c:forEach> 
                            <tr>
                                    <td style="padding-right: 60px;" >
                                    <input type="hidden" id="status" name="status">
                                    <input class="exportbutton" type="button" style="margin-left: 7px; float: right" onclick="getExcelReport()" value="Export"/>
                                    <input name="go" class="gobutton" onclick="return getFilterList()" style="float:right" type="submit" value="Go"/>
                                </td>

                            </tr>

                        </table>
                   </div>
                </form>
            </div>           
            <div id="datadisplay" style="overflow-x:scroll">

                <table style="font-size: 10px;">
                    <thead>                        
                    <th>Employee Number</th>
                    <th>Employee Name</th>
                    <th>Gender</th>
                    <th>Birth Date</th>                   
                    <th>Join Date</th>
                    <th>Experience (Years)</th>
                    <th>Experience (Months)</th>
<!--                    <th>Address 1</th>
                    <th>Address 2</th>-->
                    <th>City</th>
                    <th>Current Work Location</th>
                    <th>BU</th>
                    <th>Practice</th>
                    <th>Parent BU</th>
                    <th>Parent Practice</th>
                    <th>RM Id</th>
                    <th>RM Name</th>
                    <th>Parent RM Id</th>
                    <th>Parent RM Name</th>
                    <th>Designation</th>                   
                    <th>Status</th>
                    <th>Category</th>
                    <th>Resigned</th>
                    <th>Relieved</th>
                    <th>Email</th>
                    <!--<th>Personal Mail</th>-->
                    <th>Band</th>
                    <th>Transfer Date</th>
                    <th>Transfer Country</th>
                    <th>Transfer City</th>
<!--                    <th>Skill</th>
                    <th>Domain</th>-->
                    <th>Source Of Hire</th>
                    <th>Exit Status</th>
                    <th>Aadhar Number</th>
                    </thead>
                    <tbody>
                        <c:if test="${fn:length(list)>0}">
                            <% int i = 0;
                                int s = 0;
                            %>
                            <c:forEach items="${list}" var="item">
                                <% s = i % 2;
                                    if (s == 0) {%>
                                <tr class="row-odd">
                                    <% } else {%>
                                <tr class="row-even">
                                    <%}%>
                                    <td>
                                        ${item.empNumber}
                                    </td>
                                    <td>
                                        ${item.empName}
                                    </td>
                                    <td>
                                        ${item.gender}
                                    </td>
                                    <td>
                                        ${item.birthDate}
                                    </td>
                                    
                                    <td>
                                        ${item.joinDate}
                                    </td>
                                    <td>
                                        ${item.expYears}
                                    </td>
                                    <td>
                                        ${item.expMonths}
                                    </td>
<!--                                    <td>
                                     <%--   ${item.address1}--%>
                                    </td>
                                    <td>
                                      <%--  ${item.address2}--%>
                                    </td>-->
                                    <td>
                                        ${item.city}
                                    </td>
                                    <td>
                                        ${item.currentWrkLocation}
                                    </td>
                                    <td>
                                        ${item.sbu}
                                    </td>
                                    <td>
                                        ${item.subSbu}
                                    </td>
                                    <td>
                                        ${item.parentSbu}
                                    </td>
                                    <td>
                                        ${item.parentSubSbu}
                                    </td>
                                    <td>
                                        ${item.rmId}
                                    </td>
                                    <td>
                                        ${item.rmName}
                                    </td>
                                    <td>
                                        ${item.parentManagerId}
                                    </td>
                                    <td>
                                        ${item.parentManagerName}
                                    </td>
                                    <td>
                                        ${item.designation}
                                    </td>
                                    
                                    <td>
                                        ${item.status}
                                    </td>
                                    <td>
                                        ${item.billable}
                                    </td>
                                    <td>
                                        ${item.resigned}
                                    </td>
                                    <td>
                                        ${item.relieved}
                                    </td>
                                    <td>
                                        ${item.email}
                                    </td>
<!--                                    <td>
                                    <%--    ${item.personalMail} --%>
                                    </td>-->
                                    <td>
                                        ${item.bandName}
                                    </td>
                                    <td>
                                        ${item.transferredDate}
                                    </td>
                                    <td>
                                        ${item.transferedCountry}
                                    </td>
                                    <td>
                                        ${item.transferCity}
                                    </td>
<!--                                    <td>
                                        ${item.skillName}
                                    </td>
                                    <td>
                                        ${item.streamName}
                                     </td>-->
                                    <td>
                                        ${item.sourceOfHire}
                                    </td>
                                    <td>
                                        ${item.exitStatus}
                                    </td>
                                    <td>
                                        ${item.finalAdhar}
                                    </td>
                                </tr>
                                <% i = i + 1;%>
                            </c:forEach>
                        </c:if>
                        <c:if test="${fn:length(list)==0}">
                            <tr  class="row-odd">
                                <td colspan="24" style="text-align: center;">
                                    No data to display
                                </td>
                            </tr>

                        </c:if>

                    </tbody>


                </table>

                <cn:if test="${paging[0] > 1}">
                    <%@include file="/WEB-INF/jsp/paging.jsp" %>
                </cn:if>
            </div>
        </div>

        <script type="text/javascript">
        var loadingDivObj = (document.all);
        var ns4 = document.layers;
        var ns6 = document.getElementById && !document.all;
        var ie4 = document.all;
        if (ns4) {
            loadingDivObj = document.loadingDiv;
        } else if (ns6) {
            loadingDivObj = document.getElementById("loadingDiv").style;
        } else if (ie4) {
            loadingDivObj = document.all.loadingDiv.style;
        }

       stopLoading();

        function stopLoading() {
            if (ns4) {
                loadingDivObj.visibility = "hidden";
            }
            else if (ns6 || ie4)
                loadingDivObj.display = "none";
        }

        function startLoading() {
            if (ns4) {
                loadingDivObj.visibility = "visible";
            }
            else if (ns6 || ie4)
                loadingDivObj.display = "block";
        }
         $(document).ready(function() {
                var empStatus = new Array();
          <c:set var="status" value="${status}"/>
             var selectedData = "${status}";
              var flag = 0;
                 if(selectedData != ''){
                     flag = 1;
                     selectedData = selectedData.split(",");
                        for (var i = 0; i < selectedData.length; i++)
                        {
                              empStatus.push(selectedData[i].replace(/['"]+/g, ''));
                        }
                 }else{
                     empStatus = ['r','b','q','t','y','o','z','n']; 
                     
                 }
          $('.checkboxArray :checkbox').each(
                    function(){
                        var checkBoxId = this.id;
                        var rowValue =  checkBoxId.substring(2);
                        var empStatusKey =  $('#empStatusId'+rowValue).val();
                         if(flag == 0){
                            if ($.inArray(empStatusKey, empStatus) === -1) {
                                  $("#"+this.id).attr('checked', true);
                            }
                         }else{
                             if ($.inArray(empStatusKey, empStatus) != -1) {
                                  $("#"+this.id).attr('checked', true);
                            }
                         }
                     })
          });


   

        </script>
    </body>
</html>
