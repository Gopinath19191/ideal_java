<%--
    Document   : bank_details
    Created on : Mar 13, 2012, 12:12:20 PM
    Author     : 15065
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    </head>
    <body>
        <div align="center"><font color="green" size="3">${success_msg}</font></div>
        <div class="container_inner">
            <div class="page_heading">
                Holiday Allowance
            </div>
        </div>
        <div class="searchBox">
            <table width="100%" border="0" >
                <form action="reports.htm" name="searchPage" method="post" id="searchPage">
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
                            <input type="hidden" id="page" name="page" value="1" />
                        </td>
                        <td width="7%" align="left"><font size="2"><b>Month :</b></font></td>
                        <td width="15%" align="left">
                            <select id="filter_month" name="month_filter">
                                <c:forEach items="${monthsList}" var="month" varStatus="monthitr">
                                    <c:set var="selMonth" value="" ></c:set>
                                    <c:if test="${month.key==formData.month_filter}">
                                        <c:set var="selMonth" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selMonth} value="${month.key}">${month.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td width="7%" align="left"><font size="2"><b>Year :</b></font></td>
                        <td width="10%" align="left">
                            <select id="filter_year" name="year_filter">
                                <c:forEach items="${yearsList}" var="year" varStatus="yearitr">
                                    <c:set var="selYear" value="" ></c:set>
                                    <c:if test="${year.key==formData.year_filter}">
                                        <c:set var="selYear" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selYear} value="${year.key}">${year.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td align="left" width="7%"><input type="submit" name="buttonName" id="submitBtn" value="Go" class="gobutton" /></td>
                        <td align="left"><input align="left" type="submit" name="excelbuttonName" id="excelsubmitBtn" value="Export" class="exportbutton" /></td>
                    </tr>
                </form>
            </table>
        </div>
        <div class="contentwrap" style="height:auto;">
            <div class="commonformheader">Reports</div>
            <table class="tableStructure" border="0">
                <tr class="headerCenter">
                    <th width="15%">Employee</th>
                    <th width="8%">Holiday Date</th>
                    <th width="17%">Project</th>
                    <th width="8%">Total Hours</th>
                    <th width="8%">Approved Hours</th>
                    <th width="8%">Created Date</th>
                    <th width="8%">Approved Date</th>
                    <th width="20%">Reason</th>
                </tr>
                <c:forEach items="${result}" var="data" varStatus="dataStatus">
                    <tr class="${dataStatus.count%2==0?'altrow':''}">
                        <td valign ="top" align="left">
                            ${data.employeeNumber} - ${data.employeeName}
                        </td>
                        <td valign ="top" align="left">
                            ${data.allowance_date}
                        </td>
                        <td valign ="top" align="left">
                            ${data.project_name}
                        </td>
                        <td valign ="top" align="left">
                            ${data.total_hours}
                        </td>
                        <td valign ="top" align="left">
                            ${data.approved_hours}
                        </td>
                        <td valign ="top" align="left">
                            ${data.created_date}
                        </td>
                        <td valign ="top" align="left">
                            ${data.approved_date}
                        </td>
                        <td valign ="top" align="left">
                            ${data.reason}
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty(result)}">
                    <tr>
                        <td colspan="7" align="center">
                            <font color="red">No Records Found.Search it by Employee Name / Employee Id to find records</font>
                        </td>
                    </tr>
                </c:if>
            </table>
            <c:if test="${paging[0] > 1}">
                <table border="0" class="paging" width="100%">
                    <tr><td height="10">&nbsp;</td></tr>
                    <tr>
                        <td>
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
                                    <a onclick="loadForm(${paging[5]})" href="javascript:;">Next ></a>
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
        </div>
    </body>
</html>

<form action="reports.htm" method="POST" name="userform" id="userform" >
    <input type="hidden" readonly name="page" id="page" value="1"  />
</form>