<%-- 
    Document   : menu
    Created on : Sep 29, 2011, 4:25:47 PM
    Author     : 14583
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="style_tab">
    <div></div>
     <div><span onclick="loadForm('employeeKra',1);" class="ticket_tab ${(actionName == 'employeeKra')?'active_tab':'ticket_tab'}">Employee Kra</span></div>
    <div><span onclick="loadForm('certification',1);" class="ticket_tab ${(actionName == 'certification')?'active_tab':'ticket_tab'}">Certification</span></div>
   
</div>


<form action="generalInfo.htm" method="POST" name="menuFormDetails" id="menuFormDetails" >
    <input type="hidden" readonly name="actionName" id="actionName" value=""  />
    <input type="hidden" readonly name="page" id="page" value="1"  />
</form>

