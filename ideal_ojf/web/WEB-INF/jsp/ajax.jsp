<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<c:if test="${getDataFor eq 'employeeName'}">
    <json:array name="items" var="item" items="${employeeName}">
        <json:object>
          <json:property name="id" value="${item.empId}"/>
          <json:property name="value" value="${item.employeeName}-${item.employeeNumber}"/>
        </json:object>
    </json:array>
</c:if>
<c:if test="${getDataFor eq 'subBandDetails'}">
    <json:array name="items" var="item" items="${subBandList}">
        <json:object>
          <json:property name="bandId" value="${item.bandId}"/>
          <json:property name="bandName" value="${item.bandName}"/>
        </json:object>
    </json:array>
</c:if>
<c:if test="${getDataFor eq 'workEmailId'}">
    <c:out value="${checkWorkEmail}"></c:out>
</c:if>
<c:if test="${getDataFor eq 'checkEmailFlag'}">
    <c:out value="${checkEmailFlag}"></c:out>
</c:if>

    