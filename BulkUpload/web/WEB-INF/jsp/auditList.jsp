<%-- 
    Document   : auditList
    Created on : 15 Mar, 2016, 9:55:59 AM
    Author     : 16113
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Audit List</title>
        <link href="${pageContext.request.contextPath}/css/auditcss.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript">
            
            function searchAuditList()
            {
                //var x= document.getElementById("filterName").length;               
                $('#auditListPage').attr("action", "searchAuditList.htm");              
               
                document.auditListPage.submit();	
            }
        </script>
        <style>
            .dataTable td,.dataTable th{
                border: 1px solid #CADFF2;
            }
        </style>
    </head>
    <body>
        <div id="main_container" style=" margin-top: 20px;">
            <div class="center_content" style="padding-top: 10px">
                <div class="page_heading">
                    Bulk Upload Audit List
                </div>
            </div> 
            <div class="searchtable" style="height:52px;width: 970px;padding: 16px; ">
                <div class="filterWrap">
                    <div class="filetrInnerWrap">
                        <form action="showAuditList.htm" name="auditListPage" method="post" id="auditListPage" >
                            <table>
                                <tr>
                                    <td><label for="employ no" style="width: 110px;" ><b>Modified By : </b></label>

                                    </td>
                                    <td>
                                        <select name="filterName" id="filterName">
                                            <option value="">Select Modifier </option>
                                            <c:forEach items="${emplist}" var="empNumbers">

                                                <option value="${empNumbers.employee_number}" ${filter.filterName == empNumbers.employee_number?'selected = "selected"':''}>${empNumbers.employee_number}</option>

                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <label for="refNo" style="width: 80px;margin-left: 20px" ><b>Module : </b></label>
                                    </td>
                                    <td>
                                        <select name="filterModule" id="filterModule">
                                            <option value="">Select Module </option>
                                            <option value="BAND & DESIGNATION" ${filter.filterModule == 'BAND & DESIGNATION'?'selected = "selected"':''}>Band & Designation</option>
                                            <option value="RM CHANGE" ${filter.filterModule == 'RM CHANGE'?'selected = "selected"':''}>RM CHANGE</option>
                                        </select>
                                    </td>
                                    <td> &nbsp;&nbsp;&nbsp; &nbsp;</td>
                                    <td> 
                                        <input type="button" name="view" value="Go" class="gobutton" onclick="searchAuditList()"  />
                                    </td>
                                </tr>                                
                            </table>


                        </form>

                    </div>
                </div>
            </div>
            <br>
            <div>
                <table class="dataTable" border="1" style="width:1000px;">
                    <tr>
                        <th>
                            Modified By
                        </th>
                        <th>
                            Modified On
                        </th>
                        <th>
                            Module
                        </th>
                        <th>
                            Data
                        </th>
                    </tr>
                    <c:forEach items="${auditList}" var="list" varStatus="i">
                        <c:choose>
                            <c:when test="${(i.count) % 2 == 0}">
                                <tr class="even">
                                </c:when>
                                <c:otherwise>
                                <tr class="odd">
                                </c:otherwise>
                            </c:choose>  
                            <td>
                                <c:out value="${list.employee_number} - ${list.first_name} ${list.last_name}"></c:out>                                
                            </td>
                            <td>
                                <c:out value="${list.modified_date}"></c:out>
                            </td>
                            <td>
                                <c:out value="${list.module_name}"></c:out>
                            </td>
                            <td>
                                <c:if test="${list.module_name eq 'RM CHANGE'}">
                                    <a href="downloadAuditExcel.htm?filename=${list.file_name}" ><c:out value="RM CHANGE"></c:out></a> 
                                </c:if>
                                <c:if test="${list.module_name eq 'BAND & DESIGNATION'}">
                                    <a href="downloadAuditExcel.htm?filename=${list.file_name}" ><c:out value="BAND & DESIGNATION"></c:out></a> 
                                </c:if>
                            </td>
                        </tr>

                    </c:forEach>
                </table>
            </div>


        </div>
    </body>
</html>
