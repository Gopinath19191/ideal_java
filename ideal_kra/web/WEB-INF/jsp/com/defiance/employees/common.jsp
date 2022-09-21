<%-- 
    Document   : common
    Created on : Apr 16, 2012, 7:41:41 PM
    Author     : 15065
--%>
<script type="text/javascript">
     function form_submit(){
         if($('#employeeName').val() == 'Search by Employee Number or Name' || $('#employeeName').val() == ''){
             $('#employeeId').val('');
         }
     }
</script>
<input type="hidden" value="${pageContext.request.contextPath}" id="base_path" />
<div align="center" style="margin:15px 0px -20px 0px;"><font color="green" size="3">${success_msg}</font></div>
<div class="container_inner">
    <div class="page_heading">
        ${(accessType == 'HR')?'Employee Personal Information':'KRA Form'}
    </div>
    <c:if test="${accessType != 'HR'}" >
    <div class="notice_page">
        <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/images/icon_alert.png" /></div>
        <div style="padding-left:20px;">
            <ul class="notice_page_ul">
                
                <li>Please validate the data before submit & It will be subjected to verify</li>
                <li>Fields marked in * are mandatory</li>
            </ul>
        </div>
    </div>
    </c:if>
</div>
<c:if test="${accessType == 'HR'}">
    <div class="searchBox">
        <table width="100%" border="0" >
            <form action="generalInfo.htm" name="searchPage" method="post" id="searchPage" onsubmit="form_submit();">
                <tr>
                    <td style="padding-left:10px;" width="35%">
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
                        <input type="hidden" id="actionName" name="actionName" value="${actionName}" />
                        <input type="hidden" id="page" name="page" value="1" />
                    </td>
                    <td align="left">
                        <input type="submit" name="buttonName" id="submitBtn" value="Find" class="submitbutton" />&nbsp;&nbsp;&nbsp;
                        <input type="submit" name="excelbuttonName" id="excelsubmitBtn" value="Export" class="exportbutton" />
                    </td>
                </tr>
                <tr><td height="10">&nbsp;</td></tr>
            </form>
            <tr>
                <td colspan="2" style="padding-left:10px;">
                    <c:set value="0"  var="sbuTS" scope="page" />
                    <c:set value="0"  var="sbuPES" scope="page" />
<!--                    <c:set value="0"  var="sbuErp" scope="page" />-->
                    <c:forEach items="${resultSet}" var="resultSet" varStatus="resultSetStatus" step="1">
                        <c:if test="${resultSet.recordStructure == 5}" >
                            <c:set value="${sbuTS+1}"  var="sbuTS" />
                        </c:if>
                        <c:if test="${resultSet.recordStructure == 2}" >
                            <c:set value="${sbuPES+1}"  var="sbuPES" />
                        </c:if>
<!--                        <c:if test="${resultSet.recordStructure == 11}" >
                            <c:set value="${sbuErp+1}"  var="sbuErp" />
                        </c:if>-->
                    </c:forEach>
                    <font size="4" color="green">No of persons Completed, <font color="orange">TS</font> : <font color="blue">${sbuTS}</font> | <font color="orange">PES</font> : <font color="blue">${sbuPES}</font> </font>

                </td>
            </tr>
        </table>
    </div>
</c:if>
