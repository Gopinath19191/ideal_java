<%--
    Document   : employeeKraList
    Created on : 7 Aug, 2017, 6:30:21 PM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KRA Page</title>
    </head>
    <script type="text/javascript">
        function changeStructureDesc(selectedValue) {
            if (selectedValue != "") {
                $.ajax({
                    url: 'getCompanyStructureX.htm',
                    type: "POST",
                    async: false,
                    data: ({structureId: selectedValue}),
                    success: function(ajaxObj) {
                        var arr = ajaxObj.split(":");
                        var mySelect = document.getElementById("practiceGroup");
                        mySelect.options.length = 0;
                        mySelect.options[0] = new Option("--Select Sub Group--", "");
                        var count = 1;
                        for (var i = 0; i < arr.length; i++) {
                            var out = arr[i].split(",");
                            if (out != '') {
                                if ($('#practice').val() != "" && $('#practice').val() == out[0]) {
                                    document.getElementById("practiceGroup").options[count + i] = new Option(out[1], out[0]);
                                    document.getElementById("practiceGroup").options[count + i].selected = true;
                                } else {
                                    document.getElementById("practiceGroup").options[count + i] = new Option(out[1], out[0]);
                                }
                            }
                        }
                    }
                });
            }
        }
    </script>
    <body>
        <form name="employeeKraForm" id="employeeKraForm" method="post">
             <tr><td>
           <input type="hidden" value="${fn:split(filterData.practiceGroupSearch,'%')[0]}" id="practice" name="practice"/>
                    </td></tr>
        <div class="container_inner"> 
            <div class="page_heading">
                KRA Report View
       
            </div>
            <div class="filetrInnerWrap">
                <label class="label">Financial Year </label>
                <select class="financial_year" name="financeYear" id="financeYear">
                    <c:forEach items="${testFin}" var="testFin" varStatus="i">
                        <option value="${testFin}" ${testFin eq current_financial_year ? 'selected="selected"':''}>${testFin}</option>
                    </c:forEach>
                </select>
                <!--<input type="hidden" id="finYear" name="financeYear" value="" readonly/>-->
                <label class="label">KRA Period </label>
                <select class="quarter" name="quarter" id="quarter">
                    <c:forEach items="${quarterList}" var="quarterList" varStatus="i">
                        <option value="${quarterList.quarter_id}" ${quarterList.quarter_id eq current_quarter ? 'selected="selected"':''}>${quarterList.quarter_name}</option>
                    </c:forEach>
                </select>
                <label class="label">Unit </label>
                <select id="companyStructureId" name="companyStructureId" class="selectbox_new" style="width:75px;" onchange="changeStructureDesc(this.value)" >
                    <option value='10'>All</option>
                    <c:forEach items="${cmpStructureList}" var="CompanyStruct" varStatus="i">
                        <option value="${CompanyStruct.id}" ${CompanyStruct.id eq requestorDTO.companyStructureId ? 'selected="selected"':''}>${CompanyStruct.name}</option>
                    </c:forEach>
                </select>
                <label class="label">Sub unit </label>
                <select id="practiceGroup" name="practiceGroup" class="selectbox_new" style="width:150px;">
                    <option value="">--Select Sub Group--</option>
                    <c:forEach items="${pgList}" var="pgListValue" varStatus="i">
                        <option value="${pgListValue.id}" ${pgListValue.id eq requestorDTO.practiceGroup ? 'selected="selected"':''}>${pgListValue.name}</option>
                    </c:forEach>
                </select>
                <!--<input type="hidden" id="quarter" name="quarter" value="" readonly/>-->
                <input type="button" name="view" value="Go" class="gobutton"  onClick="return reportFilter();" style="float:none;margin-left: 10px;"/>
                <input type="button" name="view" value="Export" class="exportbutton"   onclick="return getreportKraExcelExport();" style="float:none;margin-left: 10px;"/>
            </div>
        </div>
        <div class="feedbacks index">
            <div id="datadisplay">
                <table>
                    <tr>
                        <th align="center">Employee Id</th>
                        <th align="center">Employee Name</th>
                        <th align="center">Financial Year</th>
                        <th align="center">Quarter</th>
                        <th align="center">Status</th>
                        <th align="center">Action</th>
                    </tr>
                    <c:choose>
                        <c:when test="${fn:length(details)!=0}">
                            <c:forEach items="${details}" var="item">
                                <tr>
                                    <td>${item.employee_number}</td>
                                    <td>${item.employee_name}</td>
                                    <td>${item.financial_year}</td>
                                    <td>${item.quarter_name}</td>
                                    <td>${item.status}</td>
                                    <td align="center">
                                        <a href="viewIndividualKra.htm?kraId=${item.kra_id}&reportingManager=2&edit=1"><img src="${pageContext.request.contextPath}/images/eye.png" /></a>
                                     
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="6" style="text-align: center;"><label class="label">No data to display</label></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    
                </table>

            </div>
        </div>
        </form>
    </body>
</html>
