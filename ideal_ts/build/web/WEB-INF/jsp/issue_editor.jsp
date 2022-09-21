<%-- 
    Document   : issue_editor
    Created on : 25 Feb, 2016, 7:23:40 PM
    Author     : 16113
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control"  content="no-cache"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="0"/>
        <title>Tickets</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/filterOnChange.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />        
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
        <link href="/ideal_revamp/favicon.ico" type="image/x-icon" rel="icon" />
        <link href="/ideal_revamp/favicon.ico" type="image/x-icon" rel="shortcut icon" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ext-all.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tp.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/extjs/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/extjs/ext-all.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jscript.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
         <link href="${pageContext.request.contextPath}/css/masterCss.css" rel="stylesheet" type="text/css" />
    </head>
    
    <script type="text/javascript">
        $(document).ready(function(){
            $("#employee_search").autocomplete("employee_search_user.htm", {
                minChars: 1,
                matchContains: true
            });
            $('#employee_search').result(function(event,data,formatted){
                if(data){
                    $('#employee_id').val(data[1]);
                }
            });
        });
        
        function updateIssue(){
            var subject = document.getElementById("issueName").value;
            if(subject==null || subject==""){
                alert('Please enter a valid Subject');
                return false;
            }          
            else{
                
                $('#addFeedbackPage').attr("action", "updateIssue.htm");
                document.addFeedbackPage.submit();
                startLoading();
                return true;
            }
        }
        
        function showFeedbacksList(){
            $('#addFeedbackPage').attr("action", "showFeedbacksList.htm");
            document.addFeedbackPage.submit();
            startLoading();
        }
        
        function showList(){
            $('#addFeedbackPage').attr("action", "showListOnCancel.htm");
            document.addFeedbackPage.submit();
            startLoading();
        }
        
        function nextStatus(statusTable){
            var table = document.getElementById(statusTable);
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);
            var cell1 = row.insertCell(0);
            var element1 = document.createElement("input");
            element1.type = "text";
            element1.name="newstatus";
           cell1.appendChild(element1);            
        }
        
    </script>
</head>
<body>      
    <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150;top: 275px; left: 525px;text-align:center"><img src="${pageContext.request.contextPath}/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <div id="containerAdd">
        <div >
            <div class="page_heading">
                Edit Issue
            </div>
        </div>   
        <br><br>           
        
        <div id="commonformbg">
            <div style="background: #EEF2F8" align="center">
                <form name="addFeedbackPage" method="post" id="addFeedbackPage">
                    <div align="center" >
                  <table align="center" style="width: 100%" >
                       
                      <tr> <td class="labelling"><b>Issue Name</b></td>
                            <td class="labelling">
                                    <input type="text" value="${issueData.configuration_value}" name="issueName" id="issueName" >
                                      <input type="hidden" value="${issueData.configuration_value}" name="oldIssue" id="oldIssue" >
                                      <input type="hidden" value="${issueData.id}" name="issueId" id="issueId"></td>
                                <td class="labelling">
                                    <div id="addStatus">
                                        <table id="statusTable">
                                            <tr>
                                                <td align="center"><b>Status</b></td>
                                                
                                                <td> <a class="add" style="text-decoration:none;color: #4C83B3;margin-right: 2px;" href="javascript:nextStatus('statusTable')"></a></td>
                                            </tr>
                                            <c:forEach items="${statusList}" var="statusObj">
                                                <tr><td><input type="text" value="${statusObj.configuration_value}" name="status">
                                                        <input type="hidden" value="${statusObj.configuration_value}" name="oldStatus">
                                                        <input type="hidden" value="${statusObj.id}" name="stausId">
                                                    </td></tr>
                                              </c:forEach>
                                    
                                        </table></div>
                                </td>
                      </tr>       
                <tr>
                   <td class="labelling"> </td>
                              <td class="labelling">
                    <input type="button" value="Submit" class="submitbutton_TS" addIssue="addIssue()" onclick="updateIssue();"/>&nbsp;&nbsp;&nbsp;
                     <input type="button" value="Cancel" class="cancelbutton_TS" onclick="history.go(-1);" />

                               </td>
                            </tr>

                         </table>
                  </div>          
                </form>
                <br>
            </div>
        </div>
        <br>
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
    </div>
</body>
</html>
