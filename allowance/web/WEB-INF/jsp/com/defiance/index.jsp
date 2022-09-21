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
        <script>
            $(function() {
                $('.datePick').datepicker({
                    changeMonth: true,
                    changeYear: true,
                    maxDate: new Date,
                    dateFormat:'dd-mm-yy'
                });
            });
        </script>
    </head>
    <body>
        <div align="center"><font color="green" size="3">${success_msg}</font></div>
        <div class="container_inner">
            <div class="page_heading">
                Holiday Allowance
            </div>
            <div class="tabletools">
                <a class="menuAddOrg" title="Pending List"   href="apply.htm" target="_self" style="text-decoration:none;">Apply Holiday Allowance</a>
            </div><br/><br/>
        </div>
        <div class="searchBox">
            <table width="100%" border="0" >
                <form action="index.htm" name="searchPage" method="post" id="searchPage">
                    <table width="98%" border="0" align="center">
                        <tr>
                            <td width="10%" align="center"><font size="2"><b>From Date :</b></font></td>
                            <td width="25%" align="left">
                                <input type="text" readonly class="datePick" name="startFilter" id="startFilter" value="${stfilter}" />
                                <img alt="Refresh" title="Refresh" style="cursor:pointer" onClick="$('#startFilter').val('')" src="${pageContext.request.contextPath}/images/arrow_refresh.png" />
                            </td>
                            <td width="10%" align="center"><font size="2"><b>To Date :</b></font></td>
                            <td width="25%" align="left">
                                <input type="text" readonly class="datePick" name="endFilter" id="endFilter" value="${edfilter}" />
                                <img alt="Refresh" title="Refresh" style="cursor:pointer" onClick="$('#endFilter').val('')" src="${pageContext.request.contextPath}/images/arrow_refresh.png" />
                            </td>
                            <input type="hidden" id="page" name="page" value="1" />
                            <td align="left" width="40%"><input type="submit" name="buttonName" id="submitBtn" value="Go" class="gobutton" /></td>
                        </tr>
                    </table>
                </form>
            </table>
        </div>
        <div class="contentwrap" style="height:auto;">
            <div class="commonformheader">Holiday Allowances</div>
            <input type="hidden" value="${pageContext.request.contextPath}" id="base_path" />
            <table class="tableStructure">
                <tr class="headerCenter">
                    <th width="10%">Holiday Date</th>
                    <th width="10%">Created Date</th>
                    <th width="22%">Project</th>
                    <th width="12%">Total Hours</th>
                    <th width="12%">Approved Hours</th>
                    <th width="25%">Reason</th>
                    <th width="12%">Status</th>
                    <th width="5%">Action</th>
                </tr>
                <c:forEach items="${result}" var="data" varStatus="dataStatus">
                    <tr class="${dataStatus.count%2==0?'altrow':''}">
                        <td valign ="top" align="left">
                            ${data.allowance_date}
                        </td>
                        <td valign ="top" align="left">
                            ${data.created}
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
                            ${data.reason}
                        </td>
                        <td valign ="top" align="left">
                            ${data.statusVal}
                        </td>
                        <td valign ="top" align="center">
                            <c:if test="${(data.status == 's' || data.status == 'r') && data.saveEnable=='1'}">
                                <a href="apply.htm?allowanceId=${data.allowanceId}"><img alt="Edit" title="Edit" src="${pageContext.request.contextPath}/images/edit-icon.png" /></a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty(result)}">
                    <tr>
                        <td colspan="7" align="center">
                            <font color="red">No Records Found</font>
                        </td>
                    </tr>
                </c:if>
            </table>
            <c:if test="${paging[0] > 1}">
                <table class="paging" >
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

<form action="index.htm" method="POST" name="userform" id="userform" >
    <input type="hidden" readonly name="page" id="page" value="1"  />
</form>