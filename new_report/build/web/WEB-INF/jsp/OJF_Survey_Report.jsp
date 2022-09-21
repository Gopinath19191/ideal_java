<%-- 
    Document   : OJF_Survey_Report
    Created on : 13 Jul, 2022, 12:49:54 PM
    Author     : 18128
--%>

<%@include file="header.jsp" %>
<html>
    <head>
        <title> OJF Survey Report </title>
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
            startLoading();
            var from_date = $("#fromDate").val();  
            var to_date = $("#toDate").val();
                        
            var fromMessage = document.getElementById("from");
            var toMessage = document.getElementById("to");
            
            if(from_date ==""){           
                fromMessage.textContent = "Please select the from date";
                document.getElementById("fromDate").style.borderColor="#FF0000";                
                fromMessage.style.display="display";               
            }else if(to_date =="") {                 
                document.getElementById("from").style.display = "none";
                toMessage.textContent = "Please select the to date"; 
                document.getElementById("toDate").style.borderColor="#FF0000";
                toMessage.style.display="display";                
            }else{                                                   
                $.ajax({
                    type: 'POST',
                    url: 'getOJSSurveyReport.htm?from_date='+from_date+'&to_date='+to_date,
                    success: function(response) { 
                        stopLoading();
                        $("#OJFSurveryReport").submit();
                    }
                });
            }
        }
                                                                   
        $(document).ready(function(){
           
            $('#toDate').change(function () {
                document.getElementById("to").style.display = "none";
                document.getElementById("toDate").style.borderColor="#666" ; 
            });         
                     
            var today = new Date();      
            $("#fromDate").datepicker({          
                changeMonth: true,
                changeYear: true,
                dateFormat: 'yy-mm-dd',
                minDate : '2020-01-01',
                maxDate: today,
                onSelect: function () {
                    var formDate = $("#fromDate").val();                    
                    var endDate = new Date(formDate);
                    endDate = new Date(endDate.setDate(endDate.getDate() + 91));
                    if(endDate>=today){
                         endDate = new Date(today);
                    }
                                       
                    var date_format = $("#fromDate").val().split('-');               
                    var from_date_new = date_format[0]+'-'+date_format[1]+'-'+date_format[2];                   
                    $("#toDate").datepicker( "destroy" );
                    $("#toDate").datepicker({
                        changeMonth: true,
                        changeYear: true,
                        dateFormat: 'yy-mm-dd',
                        minDate: new Date(from_date_new),
                        maxDate: endDate
                    });
                    $('#toDate').val('');
                    document.getElementById("fromDate").style.borderColor="#666" ;
                }
            });
                
            var date_format=$("#fromDate").val().split('-');
            var from_date_new=date_format[2]+'-'+date_format[1]+'-'+date_format[0];           
            $("#toDate").datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: 'yy-mm-dd',
                minDate : new Date(from_date_new),
                maxDate: endDate
            });  
           
        });
        
    </script>
    <body>

        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">          
            <div class="container_inner">
                <div class="page_heading">
                    OJF Survey Report
                </div>

            </div>            
            <div class="tabletools">
                <form action="getOJFSurveryReportDownload.htm" id="OJFSurveryReport" name="OJFSurveryReport" method="POST">               
                    <table style="width: 100%">
                        <tr>
                            <td width="" style="color: #666;">
                                <b>From Date:</b> 
                                <input class="calender-field" type="text" placeholder="YYYY-MM-DD" name="from_date" id="fromDate" onchange="return getMessage()" readonly/>
                            </td>
                            <td width="" style="color: #666;">
                                <b>To Date :</b> 
                                <input class="calender-field" type="text" placeholder="YYYY-MM-DD" name="to_date" id="toDate" readonly />
                            </td>
                            <td >
                                <input type="button" class="exportbutton" id="exportQuality" name="export" value="Export" onclick="downloadExcel();"/>
                            </td>
                        </tr>                       
                    </table>
                </form>               
                <div id="datadisplay">
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
            <div class="fromMessage">
                <span id="from" style="font-size:20px" style="color:red" style="display:none"></span> 
            </div>
            <div class="toMessage">
                <span id="to" style="font-size:20px" style="color:red" style="display:none"></span>
            </div>
    </body>
</html>


