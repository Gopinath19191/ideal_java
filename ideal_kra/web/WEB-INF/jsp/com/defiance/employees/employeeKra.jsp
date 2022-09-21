<%--
    Document   : certification
    Created on : Aug 07, 2017, 12:12:20 PM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <style type="text/css">
            .filter{
                width: 100%;
                display: table;
                border: 1px solid #bdc9d1;
                background: #e2e8ec url(../img/box-strip.jpg) repeat-x top;
                padding: 10px 0px 10px 0px;
                margin-bottom: 10px;
            }
            .gobutton{
                margin-left:10px;
                float:none;
            }
        </style>
        <style type="text/css">
            #loadingDiv img{ border: none; }
            #loadingDiv{ filter: alpha(opacity = 80); ZOOM: 1}

        </style>
    </head>
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:120%;background-color:rgba(0,0,0,0.5);display: block; top: 0pt; left: 0pt; z-index:40;"><div  style="z-index: 90;position: absolute; z-index: 150; top: 300px;color:#000; left: 590px;text-align:center"><img src="${pageContext.request.contextPath}/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
                <%--<%@include file="/WEB-INF/jsp/com/defiance/employees/common.jsp" %>--%>
        <div class="container_inner"> 
            <div class="page_heading">
                Add KRA
                <div class="listLink"  style="margin-top:5px;">
                    <img src="${pageContext.request.contextPath}/images/icon_list.png">
                    <a href="listKra.htm" target="_self" style="text-decoration:none;color: #4C83B3;">List KRA</a>
                </div>
<!--                <div class="listLink">
                    <img src="${pageContext.request.contextPath}/images/arrow_upload.png" style="width:20px;">
                    <a target="_self" style="text-decoration:none;color: #4C83B3;" href="generalInfo.htm?actionName=uploadKra&page=1">Upload KRA</a>
                </div>-->
            </div>
            <div class="notice_page">
                <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/images/icon_alert.png" /></div>
                <div style="padding-left:20px;">
                    <ul class="notice_page_ul">
                        <li>Please validate the data before submit & It will be subjected to verify</li>
                        <li>Fields marked in * are mandatory</li>
                    </ul>
                </div>
            </div>
        </div>
        <form  name="employeeKraForm" id="employeeKraForm" method="post" >
             
            <div class="filetrInnerWrap">
                <input type="hidden" id="employeeId" name="employeeId" value="${employeeId}" readonly/>
                <input type="hidden" id="associateId" name="associate" value="${employeeId}" readonly/>
                <label class="label">Financial Year </label>
                <select class="financial_year" id="financialYear" name="year">
                    <c:forEach items="${testFin}" var="testFin" varStatus="i">
                        <option value="${testFin}" ${testFin eq current_financial_year ? 'selected="selected"':''}>${testFin}</option>
                    </c:forEach>
                </select>
                <label class="label">Time Period </label>
                <select class="quarter" name="quarterId" id="quarterId">
                    <c:forEach items="${quarterList}" var="quarterList" varStatus="i">
                        <option value="${quarterList.quarter_id}" ${quarterList.quarter_id eq current_quarter ? 'selected="selected"':''}>${quarterList.quarter_name}</option>
                    </c:forEach>
                </select>
<!--                <label class="label">Employee </label>
                <select class="quarter" name="associate" id="associateId"> 
                    <option value="">--Select Employee--</option>
                    <%--<c:forEach items="${associatesList}" var="associatesList">--%>

                        <option value="${associatesList.empId}" ${associatesList.empId eq employeeSearchID ? 'selected="selected"':''}>${associatesList.employeeName}</option>
                    <%--</c:forEach>--%>
                </select>-->
                <!--<input type="button" name="view" value="Export" class="exportbutton"   onclick="return getExcelExport();"/>-->
                <input type="button" name="view" value="Go" class="gobutton"  onClick="return searchEmployeeKra();"/>
            </div>
                <div class="filterWrap">
            <table cellpadding="0" cellspacing="0">
                <c:if test="${!empty(result)}">
                <tr>
                    <th>Name</th>
                    <th style="font-weight: normal;">${masterDetails.get(0).empNumber}-${masterDetails.get(0).employee_name}</th>
                    <th>Finance year</th>
                    <th style="font-weight: normal;">${masterDetails.get(0).financial_year}</th>
                    <th>KRA Period</th>
                    <th style="font-weight: normal;">${masterDetails.get(0).quarter_name}</th>

                </tr>
                <tr>
                    <th>Submitted On</th>
                    <th style="font-weight: normal;">${masterDetails.get(0).submitted_on}</th>
<!--                    <th>Approved By</th>
                    <th style="font-weight: normal;">${masterDetails.get(0).rm_name}</th>
                    <th>Approved On</th>
                    <th style="font-weight: normal;">${masterDetails.get(0).approved_on}</th>-->
                    <th>Accepted On</th>
                    <th style="font-weight: normal;">${masterDetails.get(0).accepted_on}</th>
                    <th>Status</th>
                    <th style="font-weight: normal;">${masterDetails.get(0).status}</th>
                </tr>
                </c:if>               
            </table>
        </div>
            <div class="contentwrap" id="datadisplay">
                <c:if test="${!empty(result)}">
                    <input type="hidden" id="kraId" name="kra_id" value="${result.get(0).kra_id}" readonly/>
                </c:if>
                <c:if test="${empty(result)}">
                    <input type="hidden" id="kraId" name="kra_id" value="" readonly/>
                </c:if>
                <input type="hidden" id="actionName" name="actionName" value="${actionName}" />
                <c:choose>
                    <c:when test="${!empty(result)}">
                        <input type="hidden" name="recordCount" id="recordCount" value="${fn:length(result)}" />
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="recordCount" id="recordCount" value="1" />
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${!empty(employeeSearchID)}">

                        <table>
                            <tbody>
                                <tr>
                                    <th>KRA Description <font color="red">*</font></th>
                                    <th>KRA UOM <font color="red">*</font></th>
                                    <th>KRA Target <font color="red">*</font></th>
                                    <th>Weightage (%) <font color="red">*</font></th>
                                    <c:if test="${actionValueX == 'r' || actionValueX == 'a' || actionValueX == 'p'}">
                                        <th>RM Remarks</th>
                                    </c:if>
                                    <c:if test="${actionValueX == 's' || actionValueX == 'r' || actionValueX == null}">
                                        <th>Action</th>
                                    </c:if>
                                </tr>
                                <c:forEach items="${result}" var="data" varStatus="dataStatus">
                                <tr id="TR_${dataStatus.count}">
                                <input type="hidden" name="certificationId_${dataStatus.count}" id="certificationId_${dataStatus.count}" value="${data.certificationIdX}" />
                                <input type="hidden" name="deletedTR_${dataStatus.count}" id="deletedTR_${dataStatus.count}" value="0" />
                                <input type="hidden" name="kraID_${dataStatus.count}" id="kraID_${dataStatus.count}" value="${data.kra_id}" />
                                <c:if test="${actionValueX == 's' || actionValueX == 'r'}">
                                    <td>
                                <!-- <input type="text" name="qualification_${dataStatus.count}" id="qualification_${dataStatus.count}" value="${data.qualificationX}" style="width:95%" maxlength="50" />-->
                                        <textarea rows="4" cols="50" name="qualification_${dataStatus.count}" id="qualification_${dataStatus.count}" >${data.qualificationX}</textarea>
                                        <span style="color:red;display:none;" id="qualification_error_${dataStatus.count}">*required</span>
                                    </td>
                                    <td>
                                        <input type="text"   id="krauom_${dataStatus.count}" name="krauom_${dataStatus.count}" value="${data.krauomX}"/>
                                        <span style="color:red;display:none;" id="krauom_error_${dataStatus.count}">*required</span>
                                    </td>
                                    <td>
                                        <input type="text"   id="kratarget_${dataStatus.count}" name="kratarget_${dataStatus.count}" value="${data.kratargetX}"/>
                                        <span style="color:red;display:none;" id="kratarget__error_${dataStatus.count}">*required</span>
                                    </td>
                                    <td>
                                        <input type="text" class="checkval" id="percentage_${dataStatus.count}" name="percentage_${dataStatus.count}" value="${data.percentageX}" onChange="calculateTotalWeightage()" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);" />
                                        <span style="color:red;display:none;" id="percentage_error_${dataStatus.count}">*required</span>
                                    </td>
                                    <c:if test="${actionValueX == 'r'}">
                                        <td>${data.rm_remarks}</td>
                                    </c:if>
                                    <td>
                                        <img class="addRemove" alt="Add" src="${pageContext.request.contextPath}/images/tm_add.png" onclick="addRow_Kra(${dataStatus.count});" />
                                        <c:if test="${dataStatus.count !=1}">
                                            <img class="addRemove" alt="Remove" src="${pageContext.request.contextPath}/images/tm_delete.png" onclick="deleteRow(${dataStatus.count},1)" />
                                        </c:if>
                                    </td>
                                </c:if>
                                <c:if test="${actionValueX == 'm' || actionValueX == 'a' || actionValueX == 'p'}">
                                    <td>
                                        <textarea rows="4" cols="50" name="qualification_${dataStatus.count}" id="qualification_${dataStatus.count}" readonly>${data.qualificationX}</textarea>
                                        <span style="color:red;display:none;" id="qualification_error_${dataStatus.count}">*required</span>
                                    </td>
                                    <td>
                                        <input type="text"   id="krauom_${dataStatus.count}" name="krauom_${dataStatus.count}" value="${data.krauomX}" readonly/>
                                        <span style="color:red;display:none;" id="krauom_error_${dataStatus.count}">*required</span>
                                    </td>
                                    <td>
                                        <input type="text"   id="kratarget_${dataStatus.count}" name="kratarget_${dataStatus.count}" value="${data.kratargetX}" readonly/>
                                        <span style="color:red;display:none;" id="kratarget__error_${dataStatus.count}">*required</span>
                                    </td>
                                    <td>
                                        <input type="text"  id="percentage_${dataStatus.count}" name="percentage_${dataStatus.count}" value="${data.percentageX}" readonly/>
                                        <span style="color:red;display:none;" id="percentage_error_${dataStatus.count}">*required</span>
                                    </td>
                                    <c:if test="${actionValueX == 'a' || actionValueX == 'p'}">
                                        <td>${data.rm_remarks}</td>
                                    </c:if>
                                </c:if>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty(result)}">
                                <tr id="TR_1">
                                <input type="hidden" name="certificationId_1" id="certificationId_1" value="" />
                                <input type="hidden" name="deletedTR_1" id="deletedTR_1" value="0" />
                                <td valign ="top" align="center">
                                    <!--  <input type="text" name="qualification_1" id="qualification_1" style="width:95%" maxlength="50" />-->
                                    <textarea rows="4" cols="50" name="qualification_1" id="qualification_1"></textarea>
                                    <span style="color:red;display:none;" id="qualification_error_1">*required</span>
                                </td>
                                <td valign ="top" align="center">
                                    <input type="text"   id="krauom_1" name="krauom_1" value=""/>
                                    <span style="color:red;display:none;" id="krauom_error_1">*required</span>
                                </td>
                                <td valign ="top" align="center">
                                    <input type="text"   id="kratarget_1" name="kratarget_1" value=""/>
                                    <span style="color:red;display:none;" id="kratarget__error_1">*required</span>
                                </td>
                                <td valign ="top" align="center">
                                    <input type="text"  class="checkval" id="percentage_1" name="percentage_1" value=""onChange="calculateTotalWeightage()" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);" />
                                    <span style="color:red;display:none;" id="percentage_error_1">*required</span>
                                </td>

                                <td valign ="top" align="center">
                                    <img class="addRemove" alt="Add" src="${pageContext.request.contextPath}/images/tm_add.png" onclick="addRow_Kra(1);" />
                                </td>
                                </tr>
                            </c:if>
                            <%--<c:if test="${actionValueX != 'm'}">--%>
                            <tr class="headerCenter">
                                <th colspan="2" ></th>
                                <td align="right"><label class="label">Total Weightage:</label></td>
                                <td colspan="1">
                                    <input type="text" name="total" id="total" value="${totalWeightage}"readonly/>
                                </td>
                                <c:if test="${actionValueX == 's'}">
                                    <th></th>
                                </c:if>
                                
                                <c:if test="${actionValueX == 'r'}">
                                    <th></th>
                                </c:if>
                            </tr> 
                            <%--</c:if>--%>
                            </tbody>
                        </table>
                            <input type="hidden" id="actionValue" name="actionValue" value=""/>
                <div class="buttonClass">
                    <c:if test="${actionValueX == 's' || actionValueX == 'r' || actionValueX == null}">
                        <!--<input type="button" name="buttonSubmit" id="buttonSubmit" value="Cancel" class="cancelbutton" onclick="javascript: location.href='rmListKra.htm'"/>-->
                        <!--<input type="button" name="buttonSubmit" id="buttonSubmit" value="Save" class="savebutton" onClick="return validateKra('s');"/>-->
                        <!--<input type="button" name="buttonSubmit" id="buttonSubmit" value="Submit" class="submitbutton" onClick="return validateKra('m');"/>-->
                    </c:if>
                </div>
                    </c:when>
                    <c:otherwise> 
                           <table>
                                <td colspan="6" style="text-align: center;">
                                    No data to display
                                </td>
                        </table>
                    </c:otherwise>
                </c:choose>
                
            </div>
        </form>
        <script type="text/javascript">
            var loadingDivObj=(document.all);
            var ns4=document.layers;
            var ns6=document.getElementById&&!document.all;
            var ie4=document.all;
            if (ns4){
                loadingDivObj=document.loadingDiv;
            }else if (ns6){
                loadingDivObj=document.getElementById("loadingDiv").style;
            }else if (ie4){
                loadingDivObj=document.all.loadingDiv.style;
            }

            stopLoading();

            function stopLoading(){
                if(ns4){loadingDivObj.visibility="hidden";}
                else if (ns6||ie4) loadingDivObj.display="none";
            }

            function startLoading(){
                if(ns4){loadingDivObj.visibility="visible";}
                else if (ns6||ie4) loadingDivObj.display="block";
            }

        </script>
    </body>
</html>

