<%@include file="header.jsp" %>
<html>
    <head>
        <title>unBilled Report</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
    </head>
    <style type="text/css">
        #loadingDiv img{ border: none; }
        #loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1}
        .gobutton{
            border: 1px solid #BCCFEA;
            background: none repeat scroll 0 0 #316ca8;
            color: #FFFFFF;
            float: left;
            font-weight: bold;
            height: 32px;
            margin: 0px;
            width: 50px;
        }
        .textbox_time{
                background: url(images/text-bg.gif) repeat-x scroll center top #FFFFFF;
                border: 1px solid #C4D1E0;
                float: left;
                font-family: arial;
                font-size: 12px;
                height: 20px;
                width: 80px;
        }
        .hiperlink{
            padding:0px 4px 1px 1px;        
            text-decoration:none;
            color:#15428B;
            float: right;
        }
    </style>
    <script type="text/javascript">
        
        $(document).ready(function(){
            $("#from_date").datepicker({changeMonth: true, changeYear: true, disabled : true, dateFormat: 'yy-mm-dd' });
            $("#to_date").datepicker({changeMonth: true, changeYear: true, disabled : true, dateFormat: 'yy-mm-dd' });
        });
        
        function getFilterList(){
            var start_date = $('#from_date').val().split('-');
            var end_date = $('#to_date').val().split('-');
            var prj_id = $('#project_id').val();            
            if(prj_id == null || prj_id ==''){
                alert("Please select Project");
                return false;
            }else if ($('#from_date').val() == null || $('#from_date').val() == '') {
                alert("Please select From Date.");
                return false;
            }else if($('#to_date').val() == null || $('#to_date').val() == ''){
                alert("Please select To Date.");
                return false;
            }else if (start_date[0] > end_date[0]) {
                alert("From Date should be lesser than To Date.");
                return false;
            }
            else if (start_date[1] > end_date[1] && start_date[0] == end_date[0]) {
                alert("From Date should be lesser than To Date.");
                return false;
            } else if (start_date[2] > end_date[2] && start_date[1] == end_date[1] && start_date[0] == end_date[0]) {
                alert("From Date should be lesser than To Date.");
                return false;
            }
//            else if ($('#from_date').val() != null && $('#from_date').val() != '') {
//                if ($('#to_date').val() == null || $('#to_date').val() == '') {
//                    alert("Please select To Date.");
//                    return false;
//                }
//            } else if ($('#to_date').val() != null && $('#to_date').val() != '') {
//                if ($('#from_date').val() == null || $('#from_date').val() == '') {
//                    alert("Please select From Date.");
//                    return false;
//                }
//            }
            else {
                 $('#reportPage').attr("action", "snapShotReport.htm");
                document.reportPage.submit();
                startLoading();
                return true;
            }
        }
        function exportdata(){
            var start_date = $('#from_date').val().split('-');
            var end_date = $('#to_date').val().split('-');
            var prj_id = $('#project_id').val();            
            if(prj_id == null || prj_id ==''){
                alert("Please select Project");
                return false;
            }else if ($('#from_date').val() == null || $('#from_date').val() == '') {
                alert("Please select From Date.");
                return false;
            }else if($('#to_date').val() == null || $('#to_date').val() == ''){
                alert("Please select To Date.");
                return false;
            }else if (start_date[2] > end_date[2]) {
                alert("From Date should be lesser than To Date.");
                return false;
            } else if (start_date[1] > end_date[1] && start_date[2] == end_date[2]) {
                alert("From Date should be lesser than To Date.");
                return false;
            } else if (start_date[0] > end_date[0] && start_date[1] == end_date[1] && start_date[2] == end_date[2]) {
                alert("From Date should be lesser than To Date.");
                return false;
            }
//            else if ($('#from_date').val() != null && $('#from_date').val() != '') {
//                if ($('#to_date').val() == null || $('#to_date').val() == '') {
//                    alert("Please select To Date.");
//                    return false;
//                }
//            } else if ($('#to_date').val() != null && $('#to_date').val() != '') {
//                if ($('#from_date').val() == null || $('#from_date').val() == '') {
//                    alert("Please select From Date.");
//                    return false;
//                }
//            }
            else {
                 $('#reportPage').attr("action", "exportdata.htm?attr=p");
                document.reportPage.submit();
            }          
        }
        
    </script>
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">
            <div class="container_inner">
                <div class="page_heading">
                    UnBilled Report
                </div>
            </div>
            <a href="unBillableHoursReport.htm" class="hiperlink">  Back </a>
            <div class="tabletools" >                
                <form action="" name="reportPage" method="post" id="reportPage">                    
                    <table id="searchForm">
                        <tbody>
                            <tr>
                                <td width="25%">
                                    <label for="reportDate" style="width: 80px;" ><b>From Date :</b> </label>
                                    <input name="from_date" id="from_date" class="textbox_time" value="${from_date}" />
                                </td>
                                <td width="25%">
                                    <label for="toDate" style="width: 80px;" ><b>To Date :</b> </label>
                                    <input name="to_date" id="to_date" class="textbox_time" value="${to_date}" />
                                </td>
                                <td>
                                    <label for="sbuFilter" style="width: 80px;"><b>Project :</b> </label>
                                    <select id="project_id" name="project_id" class="textbox_new">
                                        <option value="" >-- All --</option>
                                        <c:forEach items="${prjlist}" var="prj" varStatus="sbuitr">
                                            <option value="${prj.project_id}" ${lastprj == prj.project_id?'selected="selected"':''}>${prj.project_name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td width="10%">
                                    <input class="gobutton" align="middle"  onclick="getFilterList()" type="button" value="Go"/>
                                </td>
                                <td>
                                    <input class="exportbutton" align="middle" type="button" style="margin-left: 20px;" onclick="exportdata()" value="Export"/>
                                </td>
                            </tr>
                        </tbody></table>
                </form>
            </div>
            <div id="datadisplay">
<!--                <table style="font-size: 10px;">-->
                    <table style="font-size: 13px;">
                    <thead>
                    <th>Date</th>
                    <th>Project Code</th>
                    <th>Project Name</th>
                    <th>Sales Order</th>
                    <th>Total Approved Hours</th>
                    <th>Total Accrued Hours</th>
                    </thead>
                    <tbody>
                        <c:if test="${fn:length(filterdata)!=0}">                        
                            <% int i = 0;
                                int s = 0;
                            %>
                             <c:forEach items="${filterdata}" var="data">
                            <% s = i % 2;
                                if (s == 0) {%>
                            <tr class="row-odd">
                                <% } else {%>
                            <tr class="row-even">
                                <%}%> 
                                <td>${data.created_date}</td>
                                <td>${data.project_code}</td>
                                <td>${data.project_name}</td>
                                <td>${data.sales_order_name}</td>
                                <td>${data.approved_hours}</td>
                                <td>${data.accrued_hours}</td>
                            </tr>
                            <% i = i + 1;%>
                            </c:forEach>                                
                        </c:if>
                        <c:if test="${fn:length(filterdata)==0}">
                        <tr class="row-odd" >
                            <td colspan="6" style="text-align: center">
                                 No data to display.
                            </td>
                        </tr>
                       
                    </c:if>
                    </tbody>
                </table>

            </div>
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
