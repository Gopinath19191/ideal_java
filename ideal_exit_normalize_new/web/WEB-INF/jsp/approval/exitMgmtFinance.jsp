<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="com.defiance.ideal.exitMgmt.utils.CommonConfigurations" %>
<%--<%@include file="/WEB-INF/jsp/header.jsp" %>--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <body onload="changeTabClass('finApp');" >
        <%--<div class="page_heading">EPM (Exit Process Management)
        <div class="goToList">
            <img src="/ideal_exit_normalize/images/icon_list.png" title="List Request" alt="List Request">
            <a style="text-decoration:none;color: #4C83B3;margin-right: 2px;" target="_self" href="listRegnSubmittedEmp.htm?moduleId=${moduleId}&approveType=pending">List Pending Request</a>
            <img src="/ideal_exit_normalize/images/icon_list.png" title="List Request" alt="List Request">
            <a style="text-decoration:none;color: #4C83B3;" target="_self" href="listRegnSubmittedEmp.htm?moduleId=${moduleId}&approveType=processed">List Processed Request</a>
        </div>
        </div>--%>
        <span class="topheading">EPM (Exit Process Management)</span>
        <div class="generalFormContent">
                <%@include file="/WEB-INF/jsp/menu.jsp" %>
                <%@include file="/WEB-INF/jsp/commonContent.jsp" %>
                <%@include file="/WEB-INF/jsp/approval/exitMgmtFinContent.jsp" %>
        </div>
    </body>
</html>
