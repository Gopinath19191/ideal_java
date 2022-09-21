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
        .hiperlink{
            padding:1px 4px 1px 1px;        
            text-decoration:none;
            color:#15428B;
            float: right;
        }
    </style>   
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">
            <div class="container_inner">
                <div class="page_heading">
                    Associate UnBilled Reports
                </div>
            </div>
            <div id="datadisplay">
<!--                <div><input class="gobutton" align="middle"  onclick="loadSnapshot()" type="button" value="snapshot"/></div>-->
                <a href="javascript:history.go(-1)" class="hiperlink">  Back </a>
                <table style="font-size: 13px;">  
                    <thead>
                    <th>Employee Name</th>
                    <th>Billing Rate</th>                   
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
                                <td>${data.emp_name}</td>
                                <td>${data.billing_rate}</td>                                
                                <td>${data.approved_hours}</td>
                                <td>${data.accrued_hours}</td>
                            </tr>
                            <% i = i + 1;%>
                            </c:forEach>
                        </c:if>
                        <c:if test="${fn:length(filterdata)==0}">
                        <tr class="row-odd" >
                            <td colspan="4" style="text-align: center">
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
