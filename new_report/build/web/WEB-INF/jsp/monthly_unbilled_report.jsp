<%@include file="header.jsp" %>
<html>
    <head>
        <title>Monthly Report</title>
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
            float: right;
            font-weight: bold;
            height: 28px;
            margin: 0px;
            width: 70px;
            padding:1px 4px 1px 1px;
        }
        .hiperlink{
            padding:1px 4px 1px 14px;        
            text-decoration:none;
            color:#15428B;
            cursor: pointer;
/*            float: right;*/
        }
        .backlink{
            padding:2px 4px 6px 14px;        
            text-decoration:none;
            color:#15428B;
            float: right;
            font-weight: 600;            
        }
        .title_style{
                font-size: 12px;
                color: #666666;
                font-weight: bold;
        }
    </style>
    <script type="text/javascript">       
        
        function singleMonthReport(pid,minDate){
            $('#unbilledPage').attr("action", "singleMonthReport.htm?p_id="+pid+"&minDate="+minDate);
            document.unbilledPage.submit();            
            startLoading();           
        }
        function exportdata(){
            $('#unbilledPage').attr("action", "exportdata.htm?attr=h");
            document.unbilledPage.submit();                 
        }
    </script>
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">
            <div class="container_inner">
                <div class="page_heading">
                    Month Wise  UnBilled Details
                </div>               
            </div>
            <form name="unbilledPage" method="post" id="unbilledPage">            
            <div id="datadisplay">
                <c:if test="${fn:length(datalist)!=0}">
                <div class="title_style">
                    Project Name : ${datalist.get(0).projectName}
                </div>
                <div class="title_style">
                    Customer Name : ${datalist.get(0).customerName}
                </div>
                </c:if>
                <a href="projectsUnbilledReport.htm" class="backlink">  Back </a>
                <!--
                <a  onclick="loadSnapshot()" class="hiperlink">  Snapshot </a>                -->
                <table style="font-size: 13px;">
                    <thead>
                    <th>Month</th>
                    <th>Currency</th>
                    <th>UOM</th>
                    <th>To Be Invoiced Amount</th>
                    </thead>
                    <tbody>
                        <c:if test="${fn:length(datalist)!=0}">
                            <% int i = 0;
                                int s = 0;
                            %>
                             <c:forEach items="${datalist}" var="data">
                            <% s = i % 2;
                                if (s == 0) {%>
                            <tr class="row-odd">
                                <% } else {%>
                            <tr class="row-even">
                                <%}%>
                                <td><a onclick="singleMonthReport('${data.id}','${data.minDate}')" class="hiperlink" >${data.month} - ${data.year}</a></td>                     
                                <td>${data.currency}</td>
                                <td>${data.uom}</td>
                                <td>${data.to_invoiced_amount}</td>
                            </tr>
                            <% i = i + 1;%>
                            </c:forEach>
                        </c:if>
                        <c:if test="${fn:length(datalist)==0}">
                        <tr class="row-odd" >
                            <td colspan="4" style="text-align: center">
                                 No data to display.
                            </td>
                        </tr>
                       
                    </c:if>
                    </tbody>
                </table>
            </div>
         </form>
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
