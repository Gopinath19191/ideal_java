<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<% response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
<c:if test="${getDataFor eq 'CompanyStructureName'}">
  <json:array name="items" var="item" items="${structureList}">
    <json:object>
      <json:property name="structureId" value="${item.structureId}"/>
      <json:property name="structureName" value="${item.structureName}"/>
    </json:object>
  </json:array>
</c:if>
<c:if test="${getDataFor eq 'addressDetails'}">
    <%--${fn:split(empAddressDetails,'~~')[1]}--%>
    <c:out value="${empAddressDetails.addressLine1}~~${empAddressDetails.addressLine2}~~${empAddressDetails.zipCode}}"></c:out>
    <json:array name="items" var="item" items="${countryList}">
    <json:object>
      <json:property name="countryId" value="${item.countryId}"/>
      <json:property name="country" value="${item.country}"/>
    </json:object>
  </json:array>
    <%--<c:out value="${empAddressDetails.addressLine1}~~${empAddressDetails.addressLine2}~~${empAddressDetails.zipCode}"></c:out>--%>
</c:if>

    