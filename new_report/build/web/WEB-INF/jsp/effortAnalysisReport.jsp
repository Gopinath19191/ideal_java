<%--
    Document   : home    
    Author     : 15261
--%>
<%@include file="header.jsp" %>
<head>
    <title>ERP Services Effort Analysis</title>
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
            y: ${chartData.totalAvailHrs},
            color: colors[0],
            name: '<b>Total Available Hours</b> ' + ${chartData.totalAvailHrs}           
	        },
	        {y: ${chartData.compentencyTotal},
	        color: colors[1],
	        name: '<b>Competency </b>' + ${percentageValues.competency} + '%'
	        
	    	},
	    	{y: ${chartData.billedHrsTotal},
	    	color: colors[2],
	    	name: '<b>Accured Hours </b>' + ${percentageValues.billedHours} + '%'
	    	},
	        {y: ${chartData.trainingHrsTotal},
	            color: colors[3],
	            name: '<b>Training Hours </b>' + ${percentageValues.trainingHrs} + '%'
	        },
	
	        {y: ${chartData.preSalesHrsTotal},
	            color: colors[4],
	            name: '<b>' + 'Business Dev. ' + '</b>' + ${percentageValues.preSalesHrs} + '%'
	        },
	        {y: ${chartData.prjMgmtHrsTotal},
	            color: colors[5],
	            name: '<b>Project Management Hours </b>' + ${percentageValues.prjMgmtHrs} + '%'
	        },
	
	        {y: ${chartData.leaveHoursTotal},
	            color: colors[6],
	            name: '<b>Leave Hours </b>' + ${percentageValues.leaveHours} + '%'
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
               	 		type: 'pie'
               	 		
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
                categories: [${chartData.category}],
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
                    return  this.y;
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
            	type: 'pie',
                //name: 'Units',
                data: dt
               
            }]
        });
    });
        
    </script>
     <script type="text/javascript">
            function submitForm(){        	        
                document.getElementById("reportForm").action="effortAnalysisChart.htm";
                document.reportForm.submit();
                startLoading();
	        }
     </script>
    
</head>
<body>
    <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <div id="container">
        <div class="container_inner">
            <div class="page_heading">
                Effort Analysis
            </div>
        </div>
        
        
		<form name="reportForm" id="reportForm" method="POST">
            <div class="tabletools">
                <table>
                    <tr>
                        <td>
                            <label for="monthFilter" ><b>Month :</b> </label>
                            <select id="monthFilter" name="monthFilter" >
                                <%--<option value="" >-- Select Month --</option>--%>
                                <c:forEach items="${monthsMap}" var="mnth" varStatus="mnthitr">
                                    <c:set var="selMnth" value="" ></c:set>
                                    ${mnth.key==accrualData.monthFilter}
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
                        <td align="right"> <center> <input class="gobutton" onclick="submitForm()" type="button"  value="Go"/> </center> 
                    	</td>                        
                    </tr>
                    
                    
                </table>
            </div>
        </form>        
			        <div id="ChartDiv" style="width:100%;height:auto;" ></div>        
		           			
		<br/>		
		
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