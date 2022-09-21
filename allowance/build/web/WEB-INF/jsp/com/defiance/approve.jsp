<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<html>
    <head>   
        <style>
            .altrowsalt{
                background: bisque;
            }
        </style>
        <script type="text/javascript">
            function checkAll(id)
            {
                var c = new Array(); 
                c = document.getElementsByTagName('input');
                if( $('#'+id).is(':checked'))
                {
                    for (var i = 0; i < c.length; i++)
                    {
                        if (c[i].type == 'checkbox')
                        {
                            c[i].checked = true;
                        }
                    }
                }
                else{
                    for (var i = 0; i < c.length; i++)
                    {
                        if (c[i].type == 'checkbox')
                        {
                            c[i].checked = false;
                        }
                    }
                }
            }
             function approveRequest(type)
            {
                var k=0;
                var checkBoxLength;
                if(document.pmSubmitAction.chk_box.length==undefined){
                    checkBoxLength =1;
                    if(document.getElementById("chk_box1").checked)
                    { k++;}
                }else{
                    checkBoxLength = document.pmSubmitAction.chk_box.length;
                }
                if(checkBoxLength>=2){
                    for(var i=0;i<document.pmSubmitAction.chk_box.length;i++)
                    {
                        if(document.pmSubmitAction.chk_box[i].checked)
                        {k++;}
                    }
                }
                if(k>0)
                {
                    document.pmSubmitAction.status.value=type;
                    $('#pmSubmitAction').attr("action", "approve.htm?list=pending&page=1");
                    document.pmSubmitAction.submit();
                }
                else
                {
                    alert("Please check any one of the check box before approve");
                }
            }
            function apply(empId)
            {
                $('#pmSubmitAction').attr("action", "requestdetails.htm?primaryId="+empId);
                document.pmSubmitAction.submit();
            }
function Validate()
{
var selectMonth = document.getElementById("selMon").value;
var selectYear = document.getElementById("selYear").value;
//var selectMonth= selMon.options[selMon.selectedIndex];
//var selectYear= selYear.options[selYear.selectedIndex];
if((selectMonth !='') && selectYear=='' )
{
alert("please select year");
return false;
}
}
        </script>
    </head>
    <body>
        <div>
            <div class="container_inner">
                <div class="page_heading">
                    Holiday Allowance
                </div>
                <div class="tabletools">
                    <a class="menuAdd" title="Pending List"   href="approve.htm?list=pending&page=1" target="_self" style="text-decoration:none;">List Pending Request</a>
                    <a class="menuAdd" title="Processed List" href="approve.htm?list=processed&page=1" target="_self" style="text-decoration:none;">List Processed Request</a>
                </div><br/><br/>
            </div>
            <div class="searchBox">
                <table width="100%" border="0" >
                    <form id="userform" name="userform" action="approve.htm?list=${requestList}" onsubmit="return Validate()">
                        <input type="hidden" readonly name="page" id="page" value="1"  />
                        <tr>
                            <td width="35%">
                                <c:choose>
                                    <c:when test="${employeeSearchName == '' || employeeSearchName == null}">
                                        <c:set var="employeeSearchName" value="Search by Employee Number or Name"></c:set>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="employeeSearchName" value="${employeeSearchName}"></c:set>
                                    </c:otherwise>
                                </c:choose>
                                <input type="text" id="employeeName" value="${employeeSearchName}" style="width:250px;" onfocus="if(this.value=='Search by Employee Number or Name') this.value='';" onblur="if(this.value=='') this.value='Search by Employee Number or Name';" />
                                <img alt="Refresh" title="Refresh" style="cursor:pointer" onClick="refreshEmployee();" src="${pageContext.request.contextPath}/images/arrow_refresh.png" />
                                <input type="hidden" id="employeeId" name="employeeId" value="${employeeSearchID}" />

                            </td>
                            <td width="10%" align="left"><label><b>Project Name: </b></label></td>
                            <td width="15%">
                                <select id="project_name" name="project_name" style="width:90%">
                                    <option value="">--All--</option>
                                    <c:forEach items="${projectsName}" var="projectName" varStatus="i">
                                        <option ${(projectName.projectId == projectNameIdvalue) ? 'selected':''} value="${(projectName.projectId)}">${projectName.projectName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            
                            <td width="5%" align="left"><label><b>Select Year: </b></label></td>
                            <td width="13%">

                                <select name="selYear" id="selYear" onchange="" size="1" style="width:90%">
                                    <option value="">--Select Year--</option>
                                    <c:forEach items="${yearsList}" var="yearsList" varStatus="sbuitr">
                                        <c:set var="selSbu" value="" ></c:set>
                                        <option <c:if test="${formData.selYear == yearsList.value}">selected="selected"</c:if> value="${yearsList.key}">${yearsList.value}</option>
                                    </c:forEach>
                                </select>
                            </td>
                                 <td width="5%" align="left"><label><b>Select Month: </b></label></td>
                            <td width="14%">
                                                       
                                   <select name="selMon" id="selMon" onchange="" size="1" style="width:90%">
                                    <option value="">--Select Month--</option>
                                    <c:forEach items="${monthsList}" var="month" varStatus="monthitr">
                                    <c:set var="selMonth" value="" ></c:set>
                                    <c:if test="${month.key==formData.selMon}">
                                        <c:set var="selMonth" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selMonth} value="${month.key}">${month.value}</option>
                                </c:forEach>
                                  </select>
                              </td>
                            <td >
                                <input type="submit" class="gobutton" id="rmSubmit" name="rmSubmit" Value="Go"/>
                                <input type="hidden" readonly id="list" name="list" Value="${requestList}"/>
                            </td>
                            <c:if test="${requestList eq 'processed'}">
                                <td><input type="submit" name="excelbuttonName" id="excelsubmitBtn" value="Export" class="exportbutton" /></td>
                            </c:if>
                        </tr>
                    </form>
                </table>
            </div>
            <div class="contentwrap" style="height:auto;">
                <c:if test="${requestList eq 'pending'}">
                    <div class="commonformheader">PM Pending List</div>
                </c:if>
                <c:if test="${requestList eq 'processed'}">
                    <div class="commonformheader">PM Processed List</div>
                </c:if>
                <input type="hidden" readonly value="${pageContext.request.contextPath}" id="base_path" />
                <c:if test="${requestList eq 'pending'}">
                    <div style="padding-left:20px;"><input class="submitbutton" title="Approve" type="button" onclick="approveRequest('a');" value="Approve" /></div>
                </c:if>
                    
                <form id="pmSubmitAction" name="pmSubmitAction" action="" method="post">
                    <table class="tableStructure">
                        <tr>
                            <th>
                            <c:if test="${requestList eq 'pending'}">
                                <input type="checkbox" id="chkall_box" name="chkall_box" value="" onclick="checkAll(this.id);"/></th>
                            </c:if>
                            </th>
                            <th>Employee Name</th>
                            <th>Holiday Date</th>
                            <th>Created Date</th>
                            <th>Project</th>
                            <th>Timesheet Hrs</th>
                            <th>Timesheet Approved Hrs</th>
                            <th>Total Hours</th>
                            <th>Approved Hours</th>
                            <th>Reason</th>
                            <th>Status</th>
                           <th>
                            <c:if test="${requestList eq 'pending'}">
                                Action
                            </c:if>
                            </th>
                        </tr>
                        <c:if test="${!empty(result)}">
                            <c:forEach items="${result}" var="empData" varStatus="i">
                                <c:if test="${((requestList eq 'pending')) || requestList eq 'processed'}">
                                <tr class="${empData.saveEnable=='0'?'altrowsalt':''}">
                                    <td style="width: 2%;text-align: center;">
                                        <c:if test="${(requestList eq 'pending') && empData.saveEnable=='1'}">
                                            <input type="checkbox" id="chk_box${i.index+1}" name="chk_box" value="${empData.id}~~${empData.totalHours}"/>
                                        </c:if>
                                    </td>
                                    <td style="width: 24%">${empData.empName}</td>
                                    <td style="width: 9%">${empData.allowance_date}</td>
                                    <td style="width: 9%">${empData.created_date}</td>
                                    <td style="width: 25%">${empData.prajectName}</td>
                                    <td style="width: 25%">
                                        <c:choose>
                                            <c:when test="${empData.timesheetEnteredHours != null}">
                                                ${empData.timesheetEnteredHours}
                                            </c:when>
                                            <c:otherwise>
                                                NA
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td style="width: 25%">
                                        <c:choose>
                                            <c:when test="${empData.timesheetApprovedHours != null}">
                                                ${empData.timesheetApprovedHours}
                                            </c:when>
                                            <c:otherwise>
                                                NA
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td style="width: 8%">${empData.totalHours}</td>
                                    <td style="width: 7%">
                                        <c:choose>
                                            <c:when test="${empData.approvedHours != null}">
                                                ${empData.approvedHours}
                                            </c:when>
                                            <c:otherwise>
                                                ---
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td style="width: 20%">${empData.reason}</td>
                                    <td style="width: 10%">
                                        <c:if test="${empData.status != null}">
                                            <c:choose>
                                                <c:when test="${empData.status=='m' && empData.saveEnable=='1'}">
                                                    Submitted
                                                </c:when>
                                                <c:when test="${empData.status=='a'}">
                                                    Approved
                                                </c:when>
                                                <c:when test="${empData.status=='m' && empData.saveEnable=='0'}">
                                                    Elapsed
                                                </c:when>
                                                <c:otherwise>
                                                    Rejected
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                    </td>
                                    <td style="text-align:center;">
                                        <c:if test="${(requestList eq 'pending') && empData.saveEnable=='1'}">

                                                <a class="actionimg" title="Edit" style="cursor:pointer" onclick="apply('${empData.id}','${employeeId}');" target="_self" style="text-decoration:none;"><img title="Edit" src="${pageContext.request.contextPath}/images/edit-icon.png" /></a>
                                        </c:if>
                                    </td>
                                </tr>
                                </c:if>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty(result)}">
                            <tr>
                                <td colspan="10" align="center">
                                    <font color="red">No Records Found.</font>
                                </td>
                            </tr>
                        </c:if>
                        <input type="hidden" readonly id="status" name="status" value=""/>
                     </table>
                </form>
                <c:if test="${requestList=='processed'}">
                    <c:if test="${paging[0] > 1}">  
                        <table class="paging">
                            <tr><td height="10">&nbsp;</td></tr>
                            <tr>
                                <td colspan="10">
                                    <c:choose>
                                        <c:when test="${paging[1] != 1}">
                                            <a onclick="loadForm(1)" href="javascript:;"><< First</a>
                                            <a onclick="loadForm(${paging[4]})" href="javascript:;">< Previous</a>
                                        </c:when>
                                        <c:otherwise>
                                           <a href="javascript:;" class="inactive">< Previous</a>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:forEach var="page" begin="${paging[2]}" end="${paging[3]}" step="1" varStatus ="i">
                                        <c:choose>
                                            <c:when test="${page == paging[1]}">
                                                <a class="active" onclick="loadForm(${page})" href="javascript:;"><b>${page}</b></a>
                                            </c:when>
                                            <c:otherwise>
                                                <a onclick="loadForm(${page})" href="javascript:;">${page}</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${paging[1] != paging[0]}">
                                            <a onclick="loadForm(${paging[5]})" href="javascript:;">Next ></a>&nbsp;&nbsp;
                                            <a onclick="loadForm(${paging[0]})" href="javascript:;">Last >></a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="javascript:;" class="inactive">Next ></a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>   
                          <tr><td height="10">&nbsp;</td></tr>
                        </table>
                    </c:if>
                </c:if>
            </div>
        </div>
    </body>
</html>