<%--
    Document   : home
    Author     : 15261
--%>
<%@include file="header.jsp" %>
<head>
    <title>Associate Timesheet Report</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/filterOnChange.js"></script>
        <style type="text/css">
/*            #loadingDiv img{ border: none; }
            #loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1}*/
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
        </style>
    <script type="text/javascript">
    $(document).ready(function() {  
		var opt1= document.getElementById("monthFilter");
	    var selectedMonth = opt1.options[opt1.selectedIndex].text;
		//alert(selectedMonth);
		var opt2= document.getElementById("sbuFilter");
	    var selectedSBU= opt2.options[opt2.selectedIndex].text;
	    var opt3= document.getElementById("yearFilter");
	    var selectedYear = opt3.options[opt3.selectedIndex].text;
	    var displaySBU="";
	    if(selectedSBU!='All'){
	    	displaySBU = " - " + selectedSBU;
	    }
	    
	   //var colors = Highcharts.getOptions().colors;
	   var colors = ['#A47D7C', '#CB5B4B', '#89A54E', '#80699B', '#3D96AE', '#DB843D', '#92A8CD', '#F43F7C', '#B5C092','#C5A00F','#B9CA40','#590A4F'];
	    var dt = [{	    	
            y: ${result.projectWPO},
            color: colors[0]
	        },
	        {y: ${result.competency},
	        color: colors[1]
	    	},
	    	{y: ${result.noPOProject},
	    	color: colors[2]
	    	},
	        {y: ${result.businessWait},
	            color: colors[3]
	        },
	
	        {y: ${result.projectWait},
	            color: colors[4]
	        },
	        {y: ${result.nonProjectActivity},
	            color: colors[5]
	        },
	
	        {y: ${result.notEnteredHours},
	            color: colors[6]
	        },
	
	        {y: ${result.leaveHours},
	            color: colors[7]
	        },
	
	        {y: ${result.totalAvailableHrs},
	            color: colors[8]
	        },
	
	        {y: ${result.workedHours},
	            color: colors[9]
	        },
	
	        {y: ${result.appHours},
	            color: colors[10]
	        },
	        {y: ${result.billedHours},
	            color: colors[11]
	        }
    	];
	    
	    
	    
	    //var colors = Highcharts.getOptions().colors;
        var chart = new Highcharts.Chart({
            chart: {
                        inverted: false,
                        shadow: true,
                        spacingTop: 10,
                        spacingRight: 10,
                        spacingBottom: 15,
                        spacingLeft: 10,
                        style: {
                                fontFamily: '"Lucida Grande", "Lucida Sans Unicode", Verdana, Arial, Helvetica, sans-serif', // default font
                                fontSize: '11px'
                        },
                		renderTo: 'ChartDiv',
               	 		type: 'column'
               	 		
            },
            credits: {
                        enabled: false,
                        text: 'defiance-tech.com',
                        href: 'http://www.defiance-tech.com'
                },
            title: {
                text: 'Associate Consolidated Worked Hours for ' + selectedMonth + ' ' + selectedYear + displaySBU
            },
            xAxis: {
                categories: [${result.category}],
                tickColor: '#CCC'
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                },
                stackLabels: {
                    enabled: true,
                    style: {
                        fontWeight: 'bold',
                        color: 'grey'
                    }
                }
            },
            legend: {
                enabled: false,
                        align: 'center',
                        layout: 'horizontal',
                        borderWidth: 1,
                        borderColor: '#909090',
                        borderRadius: 5,
                        shadow: true,
                        style: { padding: '4px' },
                        itemStyle: { cursor: 'pointer', color: '#3E576F' },
                        verticalAlign: 'bottom',
                backgroundColor: '#FFFFFF',
                x: 0,
                y: 8,
                floating: false
            },
            tooltip: {
                formatter: function() {
                    return ''+this.x +': '+ this.y +' ';
                }
            },
            plotOptions: {
                column: {
                    stacking: 'normal',
                    dataLabels: {
                        enabled: true,
                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
                    }
                }
            },
            series: [{
                //name: 'Units',
                data: dt
            }]
        });
    });
        
    </script>
     <script type="text/javascript">
            function submitForm(){        	        
	            var reportType = document.getElementById("reportFilter").value;
	            if(reportType == " "){
	                alert("Before Submit choose any one of the report type");                
	            }
	            else{            	
	                document.getElementById("reportForm").action="associateTimeSheetChart.htm";
	                document.reportForm.submit();
	                startLoading();
	            }
	        }
     </script>
    
    
    
    
</head>
<body>
    <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <div id="container">
        <div class="container_inner">
            <div class="page_heading">
                Associate Consolidated Worked Hours
            </div>
        </div>
        <form name="reportForm" id="reportForm" method="POST" >       
            <div class="tabletools">
                <table>
                    <tr>
                        <td>                        	
                            <label for="monthFilter" ><b>Month :</b> </label>                            
                            <select id="monthFilter" name="monthFilter" >
                                <%--<option value="" >-- Select Month --</option>--%>
                                <c:forEach items="${monthsMap}" var="mnth" varStatus="mnthitr">
                                    <c:set var="selMnth" value="" ></c:set>                                    
                                    <c:if test="${mnth.key==accrualData.monthFilter}">
                                        <c:set var="selMnth" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option  ${selMnth} value="${mnth.key}">${mnth.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <label for="yearFilter" ><b>Year :</b> </label>
                            <select id="yearFilter" name="yearFilter" >
                                <%--<option value="" >-- Select Year --</option>--%>
                                <c:forEach items="${yearsMap}" var="yr" varStatus="yritr">
                                    <c:set var="selYr" value="" ></c:set>
                                    <c:if test="${yr.key==accrualData.yearFilter}">
                                        <c:set var="selYr" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selYr} value="${yr.key}">${yr.value}</option>
                                </c:forEach>
                            </select>

                        </td>
                        <td>
                            <label for="sbuFilter" ><b>SBU :</b> </label>
                            <select id="sbuFilter" name="sbuFilter" >
                                <option value="All" >All</option>
                                <c:forEach items="${sbuMap}" var="sbu" varStatus="sbuitr">
                                    <c:set var="selSbu" value="" ></c:set>
                                    <c:if test="${sbu.key==accrualData.sbuId}">
                                        <c:set var="selSbu" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selSbu} value="${sbu.key}">${sbu.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td> <label for="reportFilter" ><b>Report :</b> </label>
                            <select id="reportFilter" name="reportFilter" >
                                <option value=" " >-- Select Report --</option>
                                <c:forEach items="${reportNameMap}" var="reportNamelist" varStatus="reportNameListitr">
                                    <c:set var="selReport" value="" ></c:set>
                                    <c:if test="${reportNamelist.configkey==accrualData.reportFilter}">
                                        <c:set var="selReport" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selReport} value="${reportNamelist.configkey}"> ${reportNamelist.configValue} </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td> <center> <input class="gobutton" onclick="submitForm()" type="button"  value="Go"/> </center> 
                    </td>
                    </tr>
                </table>
            </div>
        </form>


		<div id="ChartDiv" style="width:99%;height:auto;" ></div>

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