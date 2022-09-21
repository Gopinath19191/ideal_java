 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Partner Consultant</title>
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
        <style>
            #content{
                background: none;
            }
            .container_inner{
                margin:0px;
            }
            .label{
                font-weight: bold;
                margin-left: 5px;
                margin-right: 5px;
                color:#666666;
                padding:0px;
            }
            .filetrInnerWrap{
                background: url(../images/box-strip.jpg) repeat-x scroll top center #E2E8EC;
                border: 1px solid #BDC9D1;
                margin: 10px 0;
                height:30px;
                padding: 16px;
                border-collapse:collapse;
                text-align:left;
                padding:20px 20px;
                line-height: 3;
            }
            .date_picker{
                width:75px;
                text-align: center;
                float:left;
            }
            .listLink{
                float: right;
                color: #4C83B3;
                font-weight: bold;
                font-size: 12px;
                margin-right: 10px;
            }
            .gobutton {
                background: none repeat scroll 0 0 #316ca8;
                border: 1px solid #BCCFEA;
                color: #FFFFFF;
                float: right;
                font-weight: bold;
                height: 32px;
                width: 50px;
                margin:0px;
                border-radius: 5px;
                cursor: pointer;
            }
            .exportbutton{
                float:right;
                margin-left:10px;
                border-radius: 5px;
                cursor: pointer;
            }
            input[type=text] {
                border:1px solid #C4D1E0;
                background: white url('../images/text-bg.gif') repeat;
                font-family: 'Arial';
                margin-top: 8px;
                height:18px;
            }
        </style>
        <script type="text/javascript">
            
            $(document).ready(function() {
                $("#fstartdate,#fenddate").keydown(function() { 
                    return false;
                });
                
//                var aa=$("#fstartdate").val();
//              if(aa!=''){
//                    aa = aa.split("-");
//                    $("#fenddate").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true, changeYear: true,maxDate: '0'});
//                    $("#fenddate").datepicker("option", "minDate", new Date(aa[2], --aa[1], aa[0]));
//                     
//                }else{
//                   
//                }
                  
                $( "#fenddate" ).click(function() {
                    var frmDt=$("#fstartdate").val();
                    if(frmDt==""){
                        alert("Please select start Date");
                    }else{

                    }
                });
                
                $('#fstartdate').datepicker({dateFormat: 'dd-mm-yy', changeMonth: true, changeYear: true,minDate: '01-04-2015',
                    onSelect: function(dateText, inst) {
                        var selectedDate = dateText.split("-");
                        $("#fenddate").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true, changeYear: true});
                        $("#fenddate").datepicker("option", "minDate", new Date(selectedDate[2], --selectedDate[1], selectedDate[0]));
                        setTimeout(function(){
                            $( "#fenddate" ).datepicker('show');
                        }, 16);     
                    }});
            
                $("#employee_search").autocomplete("pc_search_userns.htm", {
                    minChars: 1,
                    matchContains: true
                });
                $('#employee_search').result(function(event, data, formatted) {
                    if (data) {
                        $('#employee_id').val(data[1]);
                    }
                });
            });
            function getExcelReportns() {
                var fstartdate = document.getElementById("fstartdate").value;
                var fenddate = document.getElementById("fenddate").value;
                if (fstartdate == null || fstartdate == "") {
                    alert('Please Select Contract startdate');
                    return false;
                }
                else if (fenddate == null || fenddate == "") {
                    alert('Please Select contract end date');
                    return false;
                }else{
                $('#pcListPage').attr("action", "excelexportPcListns.htm");
                document.pcListPage.submit();
                }
            }
            
            function getFilterList() {
                $('#pcListPage').attr("action", "pcList.htm");
                document.pcListPage.submit();
                startLoading();
            }
            
            function addConsultant() {
                $('#pcListPage').attr("action", "add_pc.htm");
                document.pcListPage.submit();
                startLoading();
            }
            function downloadFileRF(filePath, originalName) {
             $('#pcListPage').attr("action", "downloadFileRF.htm?filePath=" + filePath + "&originalName=" + originalName);
                    document.pcListPage.submit();
             }
            function searchFeedbackns(pageNo) {
                var empVal = $('#employee_search').val();
                var fstartdate = document.getElementById("fstartdate").value;
                var fenddate = document.getElementById("fenddate").value;
                if (fstartdate == null || fstartdate == "") {
                    alert('Please Select Contract startdate');
                    return false;
                }
                else if (fenddate == null || fenddate == "") {
                    alert('Please Select contract end date');
                    return false;
                }else{
                    $('#pcListPage').attr("action", "search_consultantns.htm?empname=" + empVal);
                    document.pcListPage.submit();
                    startLoading();
                }
               
            }
           
        </script>
    </head>
    <body>
        <div id="content">
            <div class="container_inner" style="padding-top: 60px">
                <div class="page_heading">
                    Partner Consultant
                    
                </div>
                <div class="filetrInnerWrap">
                    <form action="pcList.htm" name="pcListPage" method="post" id="pcListPage">
                        <%
                            String refNoString = (String) request.getAttribute("refNoString");
                            if (refNoString == null || "".equalsIgnoreCase(refNoString)) {
                                refNoString = "";
                            }
                            String pageNo = (String) request.getAttribute("page");

                            if (pageNo == null || "".equalsIgnoreCase(pageNo)) {
                                pageNo = "1";
                            }
                        %>

                            <input id="page" name="page" value="1" type="hidden">
                            <label for="empName" class="label">Consultant Name:</label>
                            <input type="text" id="employee_search" name="employee_search" style="width:250px;float:left;color: #717171;" 
                                           value="${filterData.empName == '' || filterData.empName == null? 'Search by Employee Number or First/Last name' : filterData.empName}"  
                                           onblur="if (this.value == '')
                                               this.value = 'Search by Employee Number or First/Last name';" 
                                           onfocus="if (this.value == 'Search by Employee Number or First/Last name')
                                               this.value = '';"  />	
                            <input type="hidden" id="employee_id" name="employee_id" readonly />
                            <label for="date" class="label">Contract Start Date:</label></td>
                            <input type="text" class="date_picker"  id="fstartdate" name="fstartdate"  class ="textbox"  value="${fstartdate}" readonly/>
                            <label for="date" class="label">Contract End Date:</label></td>
                            <input type="text" class="date_picker" id="fenddate" name="fenddate"  class ="textbox"  value="${fenddate}" readonly/>
                            
                            <input type="button" name="view" value="Export" class="exportbutton"   onclick="javascript:getExcelReportns();"/>
                            <input type="button" name="view" value="Go" class="gobutton"  onclick="javascript:searchFeedbackns(<%=pageNo%>);"/>
                    </form>
                </div>
                <br>
                <div class="feedbacks index">
                    <div id="datadisplay">
                       <table cellpadding="0" cellspacing="0">
                            <tr>
                                <th align="center">Partner Consultant Code</th>
                                <th align="center">Partner Consultant Name</th>
                                <th align="center">Partnering Company Name</th>
                                 <th align="center">WorkLocation</th>
                                <th align="center">Mobile No</th>
                                <th align="center">Email Address</th>
                                <th align="center">HTL Reporting Manager</th>
                                <th align="center">Practice Group</th>
                                <th align="center">Contract Start Date</th>
                                <th align="center">Contract End Date</th>
                                <th align="center">HTL – Project Code</th>
                                <th align="center">Request Form</th>
                            </tr>
                            <c:forEach items="${details}" var="item">
                                <tr>
                                    <td>${item.pc_code}</td>
                                    <td>${item.consultantName}</td>
                                    <td>${item.company}</td>
                                    <td>${item.shortcode}</td>
                                    <td>${item.pmobile}</td>
                                    <td>${item.mail}</td>
   
                                    <td>${item.rmName}</td>
                                    <td>${item.practiceGroupName}</td>
                                  
                                    <td>${item.startdate}</td>
                                    <td>${item.enddate}</td>
                                    <td>${item.projectName}</td>
                                    <td>
                                    <a  href ="javascript:;" onclick="downloadFileRF('${item.fullFileName}', '${item.attachment_file_path}');">${item.attachment_file_path}</a>
                                    <input type="hidden" readonly id="fullFileName" name="fullFileName" value="${item.attachment_file_path}"/>
                                </td>
                                </tr>
                            </c:forEach>
                        </table>
<!--                        <cn:if test="${paging[0] > 1}">
                        <%@include file="/WEB-INF/jsp/paging.jsp" %>
                    </cn:if>-->
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
