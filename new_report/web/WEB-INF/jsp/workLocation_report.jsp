<%-- 
    Document   : workLocation_report
    Created on : 28 Jul, 2022, 12:56:53 PM
    Author     : 18128
--%>

<%@include file="header.jsp" %>
<html>
    <head>
        <title> Work Location Report </title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
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
        span.ui-icon.ui-icon-circle-triangle-w, span.ui-icon.ui-icon-circle-triangle-e {
            text-indent: 0px;
            font-size: 8px;
            width: 22px;
            padding: 3px 0px 0px 0px;
        }
        .fromMessage {
            color: #FF0000;
            text-align: right;
            font-size: 8px;
        }
        .toMessage {
            color: #FF0000;
            text-align: right;
            font-size: 8px;
        }
    </style>
    <script type="text/javascript">

       function downloadExcel(){

            $.ajax({
                type: 'POST',
                url: "getWorkLocationReportXL.htm",
                success: function(response) {                        
                    $("#workLocationReport").submit();
                }
            });
        }



    </script>
    <body>

        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">          
            <div class="container_inner">
                <div class="page_heading">
                    Work Location Report
                </div>

            </div>            
            <div class="tabletools">
                <form action="workLocationReportDownload.htm" id="workLocationReport" name="workLocationReport" method="POST" onclick="downloadExcel()">               
                    <table style="width: 100%">
                        <tr>
                            <td >
                                <input type="button" class="exportbutton" id="exportQuality" name="export" value="Export"/>
                            </td>
                        </tr>

                    </table>
                </form>               
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
    </body>
</html>