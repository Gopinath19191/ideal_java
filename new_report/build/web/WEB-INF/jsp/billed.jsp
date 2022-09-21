<%--
    Document   : billablestatus
    Created on : Oct 13, 2011, 5:20:42 PM
    Author     : 14053
--%>
<%@include file="header.jsp" %>
<head>
    <title>Utilization Report-Project Associate</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.5.custom.min.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>--%>
<!--    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.min.js"></script>-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.8.5.custom.css" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/filterOnChange.js"></script>
    <style type="text/css">
        #loadingDiv img{ border: none; }

        #loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1}

        #contentWrapper{
            width:90%;
            margin-left:5%;
            border:1px solid #ccc;
            float:left;
        }
        .contentGroup{
            width:30%;
            float:left;
            padding:1%;
        }
        .contentLabel{
            width:49%;
            float:left;
        }
        .contentField{
            width:49%;
            float:left;
        }

        #datadisplay{
            overflow-x:scroll;
        }
        #datadisplay table td{
            border:1px solid #ccc;
            overflow-x:scroll;
        }
        #datadisplay table{
            border-collapse: collapse;
        }
        #datadisplay table th.dayClass{
            width:40px;
        }
        /* #datadisplay table th.weekEndClass, */
        #datadisplay table td.weekEndClass{
            background-color: #eee;
            background-image: none;
        }
        #datadisplay table td.effortChanged{
            background-color: #FF8888;
            background-image: none;
        }
        .effortChangedDiv{
            background-color: #FF8888;
            background-image: none;
            width:3px;
            height:7px;
            float:left;
        }
        #effortsLegend{
            margin-left:-310px;
        }
        #datadisplay table tr.even{
            background-color: #D0DEF0;
        }
        #datadisplay table tr.odd{
            background-color: transparent;
        }
        #reportForm label{
            width: auto;
        }
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

        <%--  $(document).ready(function() {
                $( ".jQcalendarStDate").datepicker({
                  changeMonth: true,
                changeYear: true,
                  dateFormat:"dd-M-yy",
                  altField: "#startDate",
                  altFormat: "yy-m-dd"
                  }
              );
                $(".jQcalendarEdDate").datepicker({
                  changeMonth: true,
                changeYear: true,
                  dateFormat:"dd-M-yy",
                  minDate:$(".jQcalendarStDate").val(),
                  altField: "#endDate",
                  altFormat: "yy-m-dd"
                  }
                  );

                $( ".toDate").datepicker( "option", "dateFormat", $( this ).val() );
        });--%>
            function submitForm(){
                document.getElementById("reportForm").action="billablehoursreport.htm";
                if($("#yearFilter").val() == null || $("#yearFilter").val() == ""){
                    alert("'Year' and 'Month' fields are mandatory.");
                }else if($("#monthFilter").val() == null || $("#monthFilter").val() == ""){
                    alert("'Year' and 'Month' fields are mandatory.");
                }else{
                document.reportForm.submit();
                startLoading();
                }
            }
            function exportToExcel(){

                document.getElementById("reportForm").action="billablehoursexport.htm";
                if($("#yearFilter").val() == null || $("#yearFilter").val() == ""){
                    alert("'Year' and 'Month' fields are mandatory.");
                }else if($("#monthFilter").val() == null || $("#monthFilter").val() == ""){
                    alert("'Year' and 'Month' fields are mandatory.");
                }else{
                document.reportForm.submit();
                }
            }
            function isNumberKey(evt)
            {
                var charCode = (evt.which) ? evt.which : event.keyCode
                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;
            }
            function changeBillable(){
                
                var v=document.getElementById('locationFilter').value;
                document.reportForm.billableFilter.options.length=0;
                alert(${filterData.billableFilter});
                if(v=="o"){
                    document.reportForm.billableFilter.options[0]=new Option("Equal to 0", "0");
                    if(${filterData.billableFilter}=="168"){
                        document.reportForm.billableFilter.options[1]=new Option("<=168", "168",true,true);
                    }else{
                        document.reportForm.billableFilter.options[1]=new Option("<=168", "168",false,false);
                    }
                }
                else if(v=="s"){
                    document.reportForm.billableFilter.options[0]=new Option("Equal to 0", "0");
                    if(${filterData.billableFilter}=="189"){
                        document.reportForm.billableFilter.options[1]=new Option("<=189", "189",true,true);
                    }else{
                        document.reportForm.billableFilter.options[1]=new Option("<=189", "189",false,false);
                    }
                }
                else{
                    document.reportForm.billableFilter.options[0]=new Option("Equal to 0", "0");
                    
                }
                
                //document.getElementById("billableFilter").removeAttribute("options");
            }
           
            //                $(function() {
            //
            //                    $( "input#nameFilter" ).autocomplete({
            //                        source: [${project}]
            //                    });
            //                });
            function changeProject(){
                $('#nameFilter').find('option').remove()
                $("#nameFilter option").removeAttr("selected");
                var sbuId=$("#sbuFilter").val();
                var subsbuId=$("#subSbu").val();
                $.post("getsbuajax.htm", { id:sbuId,subId:subsbuId },
                function(data) {
                    var opt = document.createElement("OPTION");
                    opt.text = "--All--";
                    opt.value = "0";
                    document.getElementById("nameFilter").options.add(opt);
                    //alert(data)
                    var typ = data.split("|");
                    for(var i=0;i<typ.length-1;i=i+2){
                        var optn = document.createElement("OPTION");
                        optn.text = $.trim(typ[i+1]);
                        optn.value = $.trim(typ[(i)]);
                        document.getElementById("nameFilter").options.add(optn);
                    }
            
                });
            }
    </script>
</head>
<body>
    <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <div id="container">
        <div class="container_inner">
            <div class="page_heading">
                Utilization Report-Project Associate
            </div>
        </div>

        <form name="reportForm" id="reportForm" action="billablehoursreport.htm" method="POST">
            <%--${filterData.monthFilter}
            ${filterData.yearFilter}
            ${filterData.projectFilter}--%>
            <div class="tabletools" >
                <table>
                    <tr>
                        <td>
                            <label for="sbuFilter" style="width: 73px;"><b>SBU : </b></label>
                            <select id="sbuFilter" name="sbuFilter" onchange="changeProject();getSubSbu(this.value);" class="textbox_new">
                                <option value="" >-- All --</option>
                                <c:forEach items="${sbuMap}" var="sbu" varStatus="sbuitr">
                                    <c:set var="selSbu" value="" ></c:set>
                                    <c:if test="${sbu.key==filterData.sbuFilter}">
                                        <c:set var="selSbu" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selSbu} value="${sbu.key}">${sbu.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td id="subPractice" >
                            <label for="subSbu" style="width: 73px;" ><b>Sub SBU</b></label>
                            <select name="subSbu" id="subSbu" class="textbox_new"  onchange="changeProject();setSbu(this.value,'sbuFilter');" >
                                <option  value="All">--Select--</option>
                                <c:forEach items="${subSbu}" var="subSbu" varStatus="i">
                                    <option  value="${subSbu.id}" ${subSbu.id eq filterData.subSbu ? 'selected="selected"':''}>${subSbu.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <label for="custFilter" style="width: 73px;"><b>Customer:</b> </label>
                            <select id="custFilter" name="custFilter" class="textbox_new">
                                <option value="" >-- All --</option>
                                <c:forEach items="${custMap}" var="cust" varStatus="custitr">
                                    <c:set var="selCust" value="" ></c:set>
                                    <c:if test="${cust.key==filterData.custFilter}">
                                        <c:set var="selCust" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selCust} value="${cust.key}">${cust.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <label for="locationFilter" style="width: 73px;"><b>Location :</b></label>
                            <select id="locationFilter" name="locationFilter" class="textbox_new">
                                <option value="" >-- All --</option>
                                <c:forEach items="${locationMap}" var="loc" varStatus="locitr">
                                    <c:set var="selL" value="" ></c:set>
                                    <c:if test="${loc.key==filterData.locationFilter}">
                                        <c:set var="selL" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selL} value="${loc.key}">${loc.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="yearFilter" style="width: 73px;"><b>Year :</b> </label>
                            <select id="yearFilter" name="yearFilter" class="textbox_new">
                                <option value="" >-- Select --</option>
                                <c:forEach items="${joinedYearsMap}" var="yr" varStatus="yritr">
                                    <c:set var="selYr" value="" ></c:set>
                                    <c:if test="${yr.key==filterData.yearFilter}">
                                        <c:set var="selYr" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selYr} value="${yr.key}">${yr.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        
                        <td>
                            <label for="monthFilter" style="width: 73px;"><b>Month :</b> </label>
                            <select id="monthFilter" name="monthFilter" class="textbox_new">
                                <option value="" >-- Select --</option>
                                <c:forEach items="${joinedMonthsMap}" var="mnth" varStatus="mnthitr">
                                    <c:set var="selMnth" value="" ></c:set>
                                    <c:if test="${mnth.key==filterData.monthFilter}">
                                        <c:set var="selMnth" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option  ${selMnth} value="${mnth.key}">${mnth.value}</option>
                                </c:forEach>
                            </select>
                        </td>

                        
                        <td>
                            <label for="pricingFilter" style="width: 73px;"><b>Pricing :</b></label>
                            <select id="pricingFilter" name="pricingFilter" class="textbox_new">
                                <option value="" >-- All --</option>
                                <c:forEach items="${pricingMap}" var="pricing" varStatus="pricingitr">
                                    <c:set var="selP" value="" ></c:set>
                                    <c:if test="${pricing.key==filterData.pricingFilter}">
                                        <c:set var="selP" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selP} value="${pricing.key}">${pricing.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="projectFilter" style="width: 73px;"><b> Project Name:</b></label>
<!--                            <input type="text" id="nameFilter" name="nameFilter" value="${filterData.nameFilter}" size="50"/>-->
                            <select id="nameFilter" name="nameFilter" class="textbox_new">
                                <option value="0" >-- All --</option>
                                <c:forEach items="${project}" var="proj" varStatus="projitr">
                                    <c:set var="selProj" value="" ></c:set>
                                    <c:if test="${proj.projId==filterData.nameFilter}">
                                        <c:set var="selProj" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selProj} value="${proj.projId}">${proj.projName}</option>
                                </c:forEach>
                            </select>

                        </td>
                        <td>
                            <label for="billedHourFilter" style="width: 73px;"><b>Billed Hours:</b></label>
                            <select class="textbox_new" id="conditionFilter" name="conditionFilter">
                                <c:forEach items="${condition}" var="con" varStatus="conitr">
                                    <c:set var="selCon" value="" ></c:set>
                                    <c:if test="${con.key==filterData.conditionFilter}">
                                        <c:set var="selCon" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selCon} value="${con.key}">${con.value}</option>
                                </c:forEach>
                            </select>
                        </td>

                        <td>

                            <input type="text" id="billableFilter" style="margin-left:78px;" class="textbox_new" name="billableFilter" onkeypress="return isNumberKey(event)" value="${filterData.billableFilter}">
                            <input type="hidden" id="hiddenFilter" name="hiddenFilter" value="1">
                        </td>
                        <td style="padding-left: 77px"><input type="button" style="float: none;margin-right: 15px;" value="Go" onclick="submitForm()" class="gobutton" />
                            <input type="button" style="float: none;" value="Export" onclick="exportToExcel()" class="exportbutton" /> </td>

                    </tr>
                    <!--                    <tr>
                                            <td><input type="button" style="float: none;" value="Go" onclick="submitForm()" class="gobutton" />
                                                <input type="button" style="float: none;" value="Export" onclick="exportToExcel()" class="exportbutton" /> </td>
                    
                                        </tr>-->
                    <%--  <tr>

                        <td width="33%">
                            <label for="startDate" >From Date : </label>
                            <input type="text" class="jQcalendarStDate" >
                            <input type="hidden" id="startDate" name="startDate"  >
                        </td>
                        <td width="33%">
                            <label for="endDate" >To Date : </label>
                            <input type="text" class="jQcalendarEdDate" >
                            <input type="hidden" id="endDate" name="endDate"  >
                        </td>
                    </tr>--%>
                </table>
            </div>
            <%--<input type="text" value="${EMP_ID}" >--%>
        </form>

        <div id="datadisplay">
            <c:if test="${fn:length(billableData)>0}">
                <table style="font-size: 10px;">
                    <thead>
                    <th>
                        Project Code
                    </th>
                    <th>
                        Project Name
                    </th>
                    <th>
                        PM Name
                    </th>
                    <th>
                        Customer Name
                    </th>
                    <th>
                        Pricing
                    </th>
                    <th>
                        SBU
                    </th>
                    <th>
                        Sub SBU
                    </th>
                    <th>
                        Location
                    </th>
                    <th>
                        Emp Id
                    </th>
                    <th>
                        Employee Name
                    </th>
                    <th>
                        Month
                    </th>
                    <th>
                        Year
                    </th>
                    <th>
                        Worked Hours
                    </th>
                    <th>
                        Approved Hours
                    </th>
                    <th>
                        Billed Hours
                    </th>
                    <th>
                        Invoiced Hours
                    </th>
                    </thead>
                    <c:forEach items="${billableData}" var="rprt" varStatus="rpritr">
                        <c:if test="${rpritr.index%2 ==0}">
                            <c:set var="rowClass" value="row-odd"></c:set>
                        </c:if>
                        <c:if test="${rpritr.index%2!=0}">
                            <c:set var="rowClass" value="row-even"></c:set>
                        </c:if>
                        <tr class="${rowClass}">
                            <td>
                                ${rprt.code}
                            </td>
                            <td style="text-wrap:normal;word-wrap:break-word">
                                ${rprt.name}
                            </td>
                            <td>
                                ${rprt.manager}
                            </td>
                            <td>
                                ${rprt.customer}
                            </td>
                            <td>
                                ${rprt.pricing}
                            </td>
                            <td>
                                ${rprt.sbu}
                            </td>
                            <td>
                                ${rprt.subSbu}
                            </td>
                            <td>
                                ${rprt.location}
                            </td>
                            <td>
                                ${rprt.empId}
                            </td>
                            <td>
                                ${rprt.empName}
                            </td>
                            <td>
                                ${rprt.month}
                            </td>
                            <td>
                                ${rprt.year}
                            </td>
                            <td>

                                ${fn:substring(fn:replace(rprt.workedHrs,':', '.'),-1,fn:length(rprt.workedHrs)-3)}
                            </td>
                            <td>

                                ${fn:substring(fn:replace(rprt.approvedHrs,':', '.'),-1,fn:length(rprt.approvedHrs)-3)}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${rprt.accrued==null || rprt.accrued==''}">
                                        ${fn:replace(rprt.accrued,':', '.')}
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${rprt.uom=='d'}">
                                                <c:choose>
                                                    <c:when test="${rprt.location=='Onsite'}">
                                                        ${fn:replace(rprt.accrued,':', '.')*8}
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${fn:replace(rprt.accrued,':', '.')*9}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:when>
                                            <c:when test="${rprt.uom=='m'}">
                                                <c:choose>
                                                    <c:when test="${rprt.location=='Onsite'}">
                                                        ${fn:replace(rprt.accrued,':', '.')*21*8}
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${fn:replace(rprt.accrued,':', '.')*21*9}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:when>
                                            <c:otherwise>
                                                ${fn:replace(rprt.accrued,':', '.')}
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>

                                </c:choose>

                            </td>
                            <td>
                                ${fn:replace(rprt.invoicedHrs,':', '.')}
                            </td>

                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${fn:length(billableData)==0}">
                <table>
                    <tr class="row-odd">
                        <td style="text-align: center;" colspan="16">
                            No data to display
                        </td>
                    </tr>

                </table>
            </c:if>
        </div>
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