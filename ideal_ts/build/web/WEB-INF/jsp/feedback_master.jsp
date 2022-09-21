<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="X-UA-Compatible" content="IE=8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control"  content="no-cache"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="0"/>
        <title>Tickets</title>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />   
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/rrf.css" />        

        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autoSuggest.mod"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demos.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/filterOnChange.js"></script>        
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />

        <link href="/ideal_revamp/favicon.ico" type="image/x-icon" rel="icon" />
        <link href="/ideal_revamp/favicon.ico" type="image/x-icon" rel="shortcut icon" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/filterOnChange.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
        <link href="${pageContext.request.contextPath}/css/masterCss.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
            $(document).ready(function () {
                $("#feedback_search").autocomplete("feedback_search.htm", {
                  
                    minChars: 1,
                    matchContains: true
                });
                $("#employee_search").autocomplete("employee_search.htm", {
                    minChars: 1,
                    width: 350,
                    matchContains: true
                });
            });
            function addIssueType() {
                $('#feedbacksListPage').attr("action", "add_issuetype.htm");
                document.feedbacksListPage.submit();
                startLoading();
            }

            function loadForm(page) {
                console.log(page);
                $('#page').val(page);
                $('#feedbacksListPage').attr("action", "showEmployees.htm");
                document.feedbacksListPage.submit();
            }
            function searchFeedback(pageNo) {
                
                var refNo = $('#feedback_search').val();
                $('#feedbacksListPage').attr("action", "search_feedback_admin.htm?referenceNo=" + refNo);
                var pageElement = document.getElementById("page");
                pageElement.value = pageNo;
                document.feedbacksListPage.submit();
                startLoading();
            }
            function getExcelReport(){
                 
                $('#feedbacksListPage').attr("action", "excelexportTicAdmin.htm");
                    
                document.feedbacksListPage.submit();
            }  
            function getFilterList(){
                $('#feedbacksListPage').attr("action", "showEmployees.htm");
                document.feedbacksListPage.submit();
                startLoading();
            }
            function getIssueForUpdate(issueId){
                $('#feedbacksListPage').attr("action", "editIssue.htm?issueId="+issueId);
                document.feedbacksListPage.submit();
                startLoading();
            }
            function deleteIssue(issueId){
                $('#feedbacksListPage').attr("action", "deleteIssue.htm?issueId="+issueId);
                document.feedbacksListPage.submit();
                startLoading();
            }
            
           
        </script>
    </head>

    <body>
        <div id="main_container">
            <div class="center_content" >
                <div class="page_heading">
                    Ticketing Master
                </div>
            </div>        
             <div class="tabletools" style="height:50px">
                <div class="filterWrap">
                    <div class="filetrInnerWrap">
                        <form action="" name="feedbacksListPage" method="post" id="feedbacksListPage" onsubmit="javascript:getData()">
 
                            <table align="right">
                                <tr>   
                                         <td>       &nbsp;&nbsp;&nbsp;    </td>   
                                    <td>    
                                        <a class="add" style="text-decoration:none;color: #4C83B3;margin-right: 2px;" href="javascript:addIssueType();"><b>Add Issue</b></a>
                                   
                                    </td>
                                    <td></td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
            <br>
          <div class="formarea" style="margin-bottom: 0px;">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <thead>
                   <tr class="formarea_header">
                            <th>Issue Type</th>
                            
                             <th>  Actions  </th>
                   </tr>
                </thead> 
                <tbody>                                     
                        <c:forEach items="${itype}" var="item" varStatus="i">
                            <c:choose>
                                    <c:when test="${i.index%2==0}">
                                        <c:set var="colorDisplay" value="formarea_row1" />
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="colorDisplay" value="formarea_row2" />
                                    </c:otherwise>
                                </c:choose>
                            <tr class="${colorDisplay}">
                                <td ><c:out value="${item.configuration_value}" /></td>                               
                               <td align="center">
                                        <img alt="edit"  src="${pageContext.request.contextPath}/images/document-blue.png" onclick="getIssueForUpdate(${item.id})" />
                                        <img class="deleterecord" alt="delete" src="${pageContext.request.contextPath}/images/document-delete.png" onclick="deleteIssue(${item.id})" />
                                    </td>
                            </tr>
                        </c:forEach>                             
               </tbody>
            </table>                    

          </div>
       </div>
    </body>
</html>
