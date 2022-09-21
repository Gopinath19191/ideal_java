<%@include file="header.jsp" %>
<html>
    <head>
        <title>Collection Report</title>
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
    </style>
    <script type="text/javascript">
        function getExcelReport(){
            $('#reportPage').attr("action", "CollectionReportXL.htm");
            document.reportPage.submit();
        }
        function getFilterList(){
            $('#reportPage').attr("action", "CollectionReport.htm");
            document.reportPage.submit();
            startLoading();
        }
        $(document).ready(function(){
            $("#fromDate").datepicker({changeMonth: true, changeYear: true, disabled : true, dateFormat: 'yy-mm-dd' });
            $("#toDate").datepicker({changeMonth: true, changeYear: true, disabled : true, dateFormat: 'yy-mm-dd' });
        });

        function loadCustomersList() {
            var business_leader_id  = $("#business_leader_id").val();
            var bdm_id = $("#bdm_id").val();
            $("#customer_id").html("<option value=''>-- All --</option>");
            if(business_leader_id != '') {
                $.ajax({
                    url: 'getCustomerList.htm',
                    type: "POST",
                    async: false,
                    data: ({business_leader_id:business_leader_id, bdm_id:bdm_id}),
                    success: function(ajaxObj) {
                        var returnData = eval(ajaxObj);
                        returnData;
                    }
                });
            }
        }

        function loadBdmList() {
            var business_leader_id  = $("#business_leader_id").val();
            $("#bdm_id").html("<option value=''>-- All --</option>");
            $("#customer_id").html("<option value=''>-- All --</option>");
            //if(business_leader_id != '') {
                $.ajax({
                    url: 'getBdmList.htm',
                    type: "POST",
                    async: false,
                    data: ({business_leader_id:business_leader_id}),
                    success: function(ajaxObj) {
                        var returnData = eval(ajaxObj);
                        returnData;
                    }
                });
                $.ajax({
                    url: 'getCustomerList.htm',
                    type: "POST",
                    async: false,
                    data: ({business_leader_id:business_leader_id}),
                    success: function(ajaxObj) {
                        var returnData = eval(ajaxObj);
                        returnData;
                    }
                });
            //}
        }
    </script>
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">
            <div class="container_inner">
                <div class="page_heading">
                    Collection Report
                </div>
            </div>

            <div class="tabletools" >
                <form action="#" name="reportPage" method="post" id="reportPage">
                    <c:if test="${access == 'BUSINESS_LEADER' }">
                        <input type="hidden" id="business_leader_id" name="business_leader_id" value="${employee_id}">
                    </c:if>
                    <table id="searchForm">
                        <tbody>
                            <tr>
                                <td width="30%">
                                    <label for="reportDate" style="width: 80px;"><b>From Date :</b> </label>
                                    <input name="fromDate" id="fromDate" class="textbox_new" value="${filterData.fromDate}" />
                                </td>
                                <td width="30%">
                                    <label for="toDate" style="width: 80px;"><b>To Date :</b> </label>
                                    <input name="toDate" id="toDate" class="textbox_new" value="${filterData.toDate}" />
                                </td>

                                <td width="30%">
                                    <label for="sbuFilter" style="width: 80px;"><b>Legal Entity :</b> </label>
                                    <select id="legal_entity" name="legal_entity" class="textbox_new">
                                        <option value="" >-- All --</option>
                                        <c:forEach items="${legal_entities}" var="legalEntity" varStatus="sbuitr">
                                            <c:set var="selSbu" value="" ></c:set>
                                            <c:if test="${legalEntity.legal_entity_id==filterData.legal_entity}">
                                                <c:set var="selSbu" value="selected='selected'" ></c:set>
                                            </c:if>
                                            <option ${selSbu} value="${legalEntity.legal_entity_id}">${legalEntity.legal_entity_code}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td width="10%">
                                    <input class="gobutton" align="middle"  onclick="getFilterList()" type="submit" value="Go"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <c:if test="${access == 'FULL'}">
                                        <label for="sbuFilter" style="width: 80px;"><b>Business Leader:</b> </label>
                                        <select id="business_leader_id" name="business_leader_id" onchange="loadBdmList();" class="textbox_new">
                                            <option value="" >-- All --</option>
                                            <c:forEach items="${business_leader}" var="businessLeader" varStatus="sbuitr">
                                                <c:set var="selSbu" value="" ></c:set>
                                                <c:if test="${businessLeader.business_leader_id==filterData.business_leader_id}">
                                                    <c:set var="selSbu" value="selected='selected'" ></c:set>
                                                </c:if>
                                                <option ${selSbu} value="${businessLeader.business_leader_id}">${businessLeader.business_leader_name}</option>
                                            </c:forEach>
                                        </select>
                                    </c:if>
                                </td>

                                <td>
                                    <c:if test="${access == 'FULL' || access == 'BUSINESS_LEADER' }">
                                        <label for="sbuFilter" style="width: 80px;"><b>Customer Owner :</b> </label>
                                        <select id="bdm_id" name="bdm_id" onchange="loadCustomersList();" class="textbox_new">
                                            <option value="" >-- All --</option>
                                            <c:forEach items="${bdm_name}" var="bdmName" varStatus="sbuitr">
                                                <c:set var="selSbu" value="" ></c:set>
                                                <c:if test="${bdmName.bdm_id==filterData.bdm_id}">
                                                    <c:set var="selSbu" value="selected='selected'" ></c:set>
                                                </c:if>
                                                <option ${selSbu} value="${bdmName.bdm_id}">${bdmName.bdm_name}</option>
                                            </c:forEach>
                                        </select>
                                    </c:if>
                                </td>

                                <td>
                                    <label for="sbuFilter" style="width: 80px;"><b>Customer :</b> </label>
                                    <select id="customer_id" name="customer_id" class="textbox_new">
                                        <option value="" >-- All --</option>
                                        <c:forEach items="${customer_name}" var="customerName" varStatus="sbuitr">
                                            <c:set var="selSbu" value="" ></c:set>
                                            <c:if test="${customerName.customer_id==filterData.customer_id}">
                                                <c:set var="selSbu" value="selected='selected'" ></c:set>
                                            </c:if>
                                            <option ${selSbu} value="${customerName.customer_id}">${customerName.customer_name}</option>
                                        </c:forEach>
                                    </select>
                                </td>

                                <td>
                                    <input class="exportbutton" align="middle" type="button" style="margin-left: 20px;" onclick="getExcelReport()" value="Export"/>
                                </td>
                            </tr>

                        </tbody></table>
                </form>
            </div>


            <div id="datadisplay" style="overflow-x: scroll;">
                <table style="font-size: 10px;">
                    <tbody>                       
                        <!--<th>Customer Code</th>-->
                    <th>Customer Name</th>
                    <th>Accounting Unit</th>
                    <th>Date of Submission</th>
                    <th>Expected Collection Date </th>
<!--                    <th>RBU</th>
                    <th>Sub RBU</th>-->
                    <th>Business Leader</th>
                    <th>Customer Owner</th>
                    <th>Project ID</th>
                    <th>Project Name</th>
                    <th>Invoice Number</th>
                    <th>Invoice Date</th>
                    <!--<th>Credit Period</th>-->
                    <th>Due Date</th>
                    <th>Invoicing  Currency</th>
                    <th>Amount in IC</th>
                    <th>Local Currency</th>
                    <th>Amount in LC</th>
                    <th>Amount Collected</th>
                    <!--<th>Days Past Due</th>-->

                    <th>TDS</th>
                    <th>Other Charges</th>
                    <th>Date of Collection</th>

                    </tbody>
                    <tbody>
                         <c:if test="${fn:length(collectionReport)!=0}">
                        <% int i = 0;
                            int s = 0;
                        %>
                        <c:forEach items="${collectionReport}" var="item">
                            <% s = i % 2;
                                if (s == 0) {%>
                            <tr class="row-odd">
                                <% } else {%>
                            <tr class="row-even">
                                <%}%> 
                                <td>
                                    ${item.cust_name}
                                </td>
                                <td>
                                    ${item.legal_entity}
                                </td>
                                <td>
                                    <fmt:formatDate var="invoiceDate" value="${item.invoice_date_submission_customer}" pattern="dd-MM-yyyy" />
                                    <fmt:formatDate var="collectionDate" value="${item.expected_collection_date}" pattern="dd-MM-yyyy" />
                                    ${invoiceDate}
                                </td>
                                <td>
                                    ${collectionDate}
                                </td>
<!--                                <td>
                                    ${item.rbu}
                                </td>
                                <td>
                                    ${item.subRBU}
                                </td>-->
                                <td>
                                    ${item.business_leader}
                                </td>
                                <td>
                                    ${item.bdm}
                                </td>
                                <td>
                                    ${item.project_code}
                                </td>
                                <td>
                                    ${item.project_name}
                                </td>
                                <td>
                                    ${item.invoice_number}
                                </td>
                                <td>
                                    ${item.invoice_date}
                                </td>
                                <!--<td>
                                ${item.credit_period}
                            </td>-->
                                <td>
                                    ${item.credit_due_date}
                                </td>
                                <td>
                                    ${item.invoicing_currency}
                                </td>
                                <td>
                                    ${item.amount_in_ic}
                                </td>
                                <td>
                                    ${item.local_currency_value}
                                </td>
                                <td>
                                    ${item.amount_in_lc}
                                </td>
                                <td>
                                    ${item.amount_collected}
                                </td>
                                <!--
                                <td>
                                ${item.days_past_due}
                            </td>
                                -->
                                <td>
                                    ${item.tds}
                                </td>
                                <td>
                                    ${item.other_charges}
                                </td>
                                <td>
                                    ${item.collection_date}
                                </td>

                            </tr>
                            <% i = i + 1;%>
                        </c:forEach>
                             </c:if>
                        <c:if test="${fn:length(collectionReport)==0}">
                        <tr class="row-odd" >
                            <td colspan="22" style="text-align: center">
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
