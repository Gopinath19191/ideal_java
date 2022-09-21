<%--
    Document   : address
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
        <%@include file="/WEB-INF/jsp/com/defiance/employees/common.jsp" %>
        <div class="contentwrap">
            <%@include file="/WEB-INF/jsp/menu.jsp" %>
            <table width="100%" border="0">
                <tbody>
                    <tr>
                        <td>
                            <form action="addressSubmit.htm" name="addressForm" id="addressForm" method="post" onSubmit="return validateAddress();">
                                <input type="hidden" id="actionName" name="actionName" value="${actionName}" />
                                <c:choose>
                                    <c:when test="${!empty(result)}">
                                        <input type="hidden" name="recordCount" id="recordCount" value="${fn:length(result)}" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="recordCount" id="recordCount" value="1" />
                                    </c:otherwise>
                                </c:choose>
                                <table class="tableStructure" border="0">
                                    <tr class="headerCenter">
                                        <c:choose>
                                            <c:when test="${accessType == 'HR'}">
                                                <th width="17%">Employee Name</th>
                                                <th width="10%">Address Type</th>
                                                <th width="15%">Address Line 1</th>
                                                <th width="15%">Address Line 2</th>
                                                <th width="8%">Country</th>
                                                <th width="8%">Region</th>
                                                <th width="9%">City</th>
                                                <th width="8%">Zip code</th>
                                                <th width="10%">Home Phone</th>
                                            </c:when>
                                            <c:otherwise>
                                                <th width="10%"><font color="red">*</font>Address Type</th>
                                                <th width="18%"><font color="red">*</font>Address Line 1</th>
                                                <th width="18%">Address Line 2</th>
                                                <th width="10%"><font color="red">*</font>Country</th>
                                                <th width="10%"><font color="red">*</font>Region</th>
                                                <th width="10%"><font color="red">*</font>City</th>
                                                <th width="10%"><font color="red">*</font>Zip code</th>
                                                <th width="10%"><font color="red">*</font>Home Phone</th>
                                                <th width="4%">Action</th>
                                            </c:otherwise>
                                        </c:choose>
                                    </tr>
                                    <c:choose>
                                        <c:when test="${accessType == 'HR'}">
                                            <c:forEach items="${result}" var="data" varStatus="dataStatus">
                                                <tr class="${dataStatus.count%2==0?'altrow':''}">
                                                    <td valign ="top" align="left">
                                                        ${data.empName}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        <c:if test="${data.address_typeX == 'p'}">
                                                            Permanent
                                                        </c:if>
                                                        <c:if test="${data.address_typeX == 'c'}">
                                                            Communication
                                                        </c:if>
                                                        <c:if test="${data.address_typeX == 'T'}">
                                                            Temporary
                                                        </c:if>
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.address_line1X}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.address_line2X}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.country_idXY}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.region_idXY}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.city_idXY}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.zip_codeX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.home_phone_numberX}
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:if test="${empty(result)}">
                                                <tr>
                                                    <td colspan="8" align="center">
                                                        <font color="red">No Records Found.Search it by Employee Name / Employee Id to find records</font>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${result}" var="data" varStatus="dataStatus">
                                                <tr id="TR_${dataStatus.count}">
                                                    <input type="hidden" name="addressId_${dataStatus.count}" id="addressId_${dataStatus.count}" value="${data.addressIdX}" />
                                                    <input type="hidden" name="deletedTR_${dataStatus.count}" id="deletedTR_${dataStatus.count}" value="0" />
                                                    <td valign ="top" align="center">
                                                        <select style="width:95%" id="address_type_${dataStatus.count}" name="address_type_${dataStatus.count}" >
                                                            <option value="">-- Type --</option>
                                                            <option ${data.address_typeX == 'c'?'selected':''} value="c" >Communication</option>
                                                            <option ${data.address_typeX == 'T'?'selected':''} value="T" >Temporary</option>
                                                            <option ${data.address_typeX == 'p'?'selected':''} value="p" >Permanent</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="address_type_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="address_line1_${dataStatus.count}" name="address_line1_${dataStatus.count}" value="${data.address_line1X}" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="address_line1_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" name="address_line2_${dataStatus.count}" id="address_line2_${dataStatus.count}" style="width:95%" value="${data.address_line2X}" maxlength="100" />
                                                        <span style="color:red;display:none;" id="address_line2_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <select style="width:95%" id="country_id_${dataStatus.count}" name="country_id_${dataStatus.count}" onChange="loadState(this.value,'region',${dataStatus.count})">
                                                            <option value="">-- Country --</option>
                                                            <c:forEach items="${countries}" var="res">
                                                                <c:choose>
                                                                    <c:when test="${res.countryId == data.country_idX}">
                                                                        <option selected value="${res.countryId}">${res.countryName}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${res.countryId}">${res.countryName}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                        <span style="color:red;display:none;" id="country_id_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="hidden" value="${data.region_idX}" id="selected_region_id_${dataStatus.count}" />
                                                        <select style="width:95%" id="region_id_${dataStatus.count}" name="region_id_${dataStatus.count}" onChange="loadState(this.value,'city',${dataStatus.count})">
                                                            <option value="">-- Region --</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="region_id_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="hidden" value="${data.city_idX}" id="selected_city_id_${dataStatus.count}" />
                                                        <select style="width:95%" id="city_id_${dataStatus.count}" name="city_id_${dataStatus.count}" >
                                                            <option value="">-- City --</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="city_id_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="zip_code_${dataStatus.count}" name="zip_code_${dataStatus.count}" value="${data.zip_codeX}" style="width:95%" maxlength="10" />
                                                        <span style="color:red;display:none;" id="zip_code_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="home_phone_number_${dataStatus.count}" name="home_phone_number_${dataStatus.count}" value="${data.home_phone_numberX}" style="width:95%" maxlength="15" />
                                                        <span style="color:red;display:none;" id="home_phone_number_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td align="center" align="center">
                                                        <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Address(${dataStatus.count});" />
                                                        <c:if test="${dataStatus.count !=1}">
                                                            <img alt="Remove" src="${pageContext.request.contextPath}/images/delete.gif" onclick="deleteRow(${dataStatus.count},1)" />
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:if test="${empty(result)}">
                                                <tr id="TR_1">
                                                    <input type="hidden" name="addressId_1" id="addressId_1" value="" />
                                                    <input type="hidden" name="deletedTR_1" id="deletedTR_1" value="0" />
                                                    <td valign ="top" align="center">
                                                        <select style="width:95%" id="address_type_1" name="address_type_1" >
                                                            <option value="">-- Type --</option>
                                                            <option value="c" >Communication</option>
                                                            <option value="T" >Temporary</option>
                                                            <option value="p" >Permanent</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="address_type_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="address_line1_1" name="address_line1_1" value="" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="address_line1_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" name="address_line2_1" id="address_line2_1" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="address_line2_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <select style="width:95%" id="country_id_1" name="country_id_1" onChange="loadState(this.value,'region',1)">
                                                            <option value="">-- Country --</option>
                                                            <c:forEach items="${countries}" var="res">
                                                                <option value="${res.countryId}">${res.countryName}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <span style="color:red;display:none;" id="country_id_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="hidden" value="" id="selected_region_id_1" />
                                                        <select style="width:95%" id="region_id_1" name="region_id_1" onChange="loadState(this.value,'city',1)">
                                                            <option value="">-- Region --</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="region_id_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="hidden" value="" id="selected_city_id_1" />
                                                        <select style="width:95%" id="city_id_1" name="city_id_1" >
                                                            <option value="">-- City --</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="city_id_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="zip_code_1" name="zip_code_1" value="" style="width:95%" maxlength="10" />
                                                        <span style="color:red;display:none;" id="zip_code_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="home_phone_number_1" name="home_phone_number_1" value="" style="width:95%" maxlength="15" />
                                                        <span style="color:red;display:none;" id="home_phone_number_error_1">*required</span>
                                                    </td>
                                                    <td align="center" align="center">
                                                        <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Address(1);" />
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>
                                </table>
                                <c:if test="${accessType != 'HR'}" >
                                    <table class="buttonClass">
                                        <tr>
                                            <td><input type="submit" name="buttonSubmit" id="buttonSubmit" value="Submit" class="submitbutton" /></td>
                                        </tr>
                                    </table>
                                </c:if>

                            </form>
                        </td>
                    </tr>
                    <c:if test="${accessType == 'HR' && paging[0] > 1}">
                        <%@include file="/WEB-INF/jsp/com/defiance/employees/paging.jsp" %>
                    </c:if>
                </tbody>
            </table>
        </div>
    </body>
</html>
<script type="text/javascript">
    $(document).ready(function(){
        for(i=1;i<=$('#recordCount').val();i++) {
            loadState($("#country_id_"+i).val(),'region',i);
            loadState($("#selected_region_id_"+i).val(),'city',i);
        }
    });
</script>

