<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
   
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <% response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
    <div class="container_inner">
        <div class="page_heading">
            Online Joining Formalities
        </div>
        <div style="float:right;" class="logout"><b>
                <a href="logout.htm">Logout</a></b></div>
        <c:if test="${groupId!=18}">
            <div class="tabletools"> 

                <a class="menuAdd" href="vendordetails.htm">Add Vendor</a>
                <a class="menuAdd" style="margin-right:10px;" href="newEmployeeAdd.htm">New Candidate</a>
                <a class="menuList" style="margin-right:10px;" href="begin.htm">List Page</a>
            </div>
        </c:if>
        <br/>
    </div>
