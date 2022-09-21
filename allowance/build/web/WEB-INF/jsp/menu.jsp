<%-- 
    Document   : menu
    Created on : Sep 29, 2011, 4:25:47 PM
    Author     : 14583
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="submenuBand">
    <ul>
        <li>
            <a href="javascript:;" onclick="loadForm('certification',1)" ><font <c:if test="${actionName == 'certification'}">color="blue"</c:if>>Certification</font></a>&nbsp;&nbsp;&nbsp;&nbsp;|
        </li>
        <li>
            <a href="javascript:;" onclick="loadForm('work_experience',1)" ><font <c:if test="${actionName == 'work_experience'}">color="blue"</c:if>>Work Experience</font></a>&nbsp;&nbsp;&nbsp;&nbsp;|
        </li>
        <li>
            <a href="javascript:;" onclick="loadForm('education',1)" ><font <c:if test="${actionName == 'education'}">color="blue"</c:if>>Education</font></a>&nbsp;&nbsp;&nbsp;&nbsp;|
        </li>
        <li>
            <a href="javascript:;" onclick="loadForm('passport',1)" ><font <c:if test="${actionName == 'passport'}">color="blue"</c:if>>Passport</font></a>&nbsp;&nbsp;&nbsp;&nbsp;|
        </li>
        <li>
            <a href="javascript:;" onclick="loadForm('visa',1)" ><font <c:if test="${actionName == 'visa'}">color="blue"</c:if>>Visa Details</font></a>&nbsp;&nbsp;&nbsp;&nbsp;|
        </li>
        <li>
            <a href="javascript:;" onclick="loadForm('skills',1)" ><font <c:if test="${actionName == 'skills'}">color="blue"</c:if>>Skills</font></a>&nbsp;&nbsp;&nbsp;&nbsp;|
        </li>
        <li>
            <a href="javascript:;" onclick="loadForm('address',1)" ><font <c:if test="${actionName == 'address'}">color="blue"</c:if>>Address</font></a>&nbsp;&nbsp;&nbsp;&nbsp;|
        </li>
        <li>
            <a href="javascript:;" onclick="loadForm('bank_details',1)"><font <c:if test="${actionName == 'bank_details'}">color="blue"</c:if>>Bank Details</font></a>&nbsp;&nbsp;&nbsp;&nbsp;|
        </li>
        <li>
            <a href="javascript:;" onclick="loadForm('emergency_contacts',1)"><font <c:if test="${actionName == 'emergency_contacts'}">color="blue"</c:if>>Emergency Contacts</font></a>
        </li>
    </ul>
</div>
<form action="generalInfo.htm" method="POST" name="menuFormDetails" id="menuFormDetails" >
    <input type="hidden" readonly name="actionName" id="actionName" value=""  />
    <input type="hidden" readonly name="page" id="page" value="1"  />
</form>

