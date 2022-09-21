<%-- 
    Document   : salesView
    Created on : 22 Jan, 2020, 4:30:26 PM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%-- <%@include file="/WEB-INF/jsp/header.jsp" %> --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="css/cake.generic.css" type="text/css"/>
        <link rel="stylesheet" href="css/ui.all.css" type="text/css"/>
        <link rel="stylesheet" href="css/jquery-ui-1.8.5.custom.css" type="text/css"/>
        <link rel="stylesheet" href="css/autoSuggest.css" type="text/css"/>
                
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.2.49.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.1.7.mod.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.5.custom.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/additional-methods.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/number_validate.js"></script>
        
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
        <title>Revenue Details</title>
        <style>
            table,td{
                border:1px solid #CEDFF1;
                border-collapse:collapse;
                text-align:center;
                color:#666666;
            }
            #tabledetails{
                margin-bottom: 100px;
            }
            #tabledetails th{
                background: url(images/table-header-strip.jpg) repeat-x scroll top center #EFF4FB;
                font-weight: bolder;
                font-size: 11px;
                padding: 15px 0px; 
                border: 1px solid #CEDFF1;
            }
            #tabledetails table tr td {
                background: none repeat scroll 0 0;
                border: 1px solid #CEDFF1;
                padding: 5px;
                font-size: 12px;
            }
            #tab{
                width: 100%;
            }
            text{
                border:1px solid #bfbfbf;
            }
            .page_heading{
                margin-top: 30px;
            }
            #header{
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
            .gobutton {
                background: none repeat scroll 0 0 #316ca8;
                border: 1px solid #BCCFEA;
                color: #FFFFFF;
                float: none;
                font-weight: bold;
                height: 32px;
                width: 50px;
                margin:0px 15px;
                border-radius: 5px;
                cursor: pointer;
            }
            .exportbutton{
                float:right;
                margin-left:10px;
                border-radius: 5px;
                cursor: pointer;
            }
            .container_inner{
                margin:0px;
            }
            #label{
                font-weight: bold;
                margin-left: 5px;
                margin-right: 5px;
                color:#666666;
            }
            #footer{
                height:35px;				
                text-align:center;
            }
            .qualitysave {
                background: url(images/icon_btn_save.png) no-repeat scroll 8px 8px #316CA8;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                margin: 0 0 0 15px;
                padding: 0 10px 0 30px;
            }
            .qualitysubmit {
                background: url(images/icon_btn_submit.png) no-repeat scroll 8px 8px #316CA8;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                margin: 0 0 0 15px;
                padding: 0 10px 0 30px;
            }
            .plainButton{
                background:#316CA8;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                margin: 0 0 0 15px;
                padding: 0 10px;
                width: auto;
                float: right;
                cursor:pointer;
            }
            .plainButton{
                float: none;
                width: auto;
                display: inline-block;
                padding:10px 10px 0px 10px;
                line-height: initial;
                border-radius: 5px;
                text-align: center;
                margin: 10px;
                font-weight:normal;
                height: 25px;
            }
            #content{
                border:1px ;
                background: none;
            }
            .error{
                text-align: center;
                color:red;
            }
            .odd {
                background-color: #FFFFFF;
            }
            .even {
                background-color: #EFF4FB;
            }
            .sucess-message{
                color:green;
            }
            #msg{
                margin-bottom:10px;
            }
            /********custom select box*******/


            .close
            {
                border:none;
                padding:10px;
                border-radius:10px;
                font-size:30px;
                color:#0000FF;
                background-color:#6699FF;
            }
            .alertboxWrap{
                background-color: rgba(0,0,0,0.3);
                width:100%;
                height:100%;
                position:absolute;
                top:0px;
                left:0px;
                display:none;
            }
            .alertbox
            {
                z-index:10;
                width:300px;
                padding:0px;
                border:blue 1px solid;
                background-color:#b3c6ff;
                position:absolute;
                top:20%;
                left:40%;
            }
            p
            {
                margin:0px;
                padding:0px;
            }
            .alertbox p:first-child
            {
                background: -webkit-linear-gradient(left, #b3ccff,#4D94DB,#CCE0F5); /* For Safari 5.1 to 6.0 */
                background: -o-linear-gradient(left,#b3ccff,#4D94DB,#CCE0F5); /* For Opera 11.1 to 12.0 */
                background: -moz-linear-gradient(left,#b3ccff,#4D94DB,#CCE0F5); /* For Firefox 3.6 to 15 */
                float:left;
                padding:0px 245px 2px 0px;
                float:left;
            }
            .alertbox p:nth-of-type(2)
            {
                padding:30px 10px 20px 10px;
                text-align:center;
            }
            button
            {
                margin:0px;
                padding:0px;
            }
            #x
            {
                width:15px;
                float:right;
            }
            .alertbox  button:nth-of-type(3)
            {
                width:55px;
                padding:2px;
                margin:0px 30px 20px 10px;
                float:right;
            }
            .alertbox button:nth-of-type(2)
            {
                width:60px;
                padding:2px;
                margin:0px 10px 20px 30px;
                float:left;
            }
            input[type=text] {
                border:1px solid #C4D1E0;
                background: white url('../images/text-bg.gif') repeat;
                font-family: 'Arial';
                width:25px;
                text-align: center;
            }
            .listLink{
                float: right;
                color: #4C83B3;
                font-weight: bold;
                font-size: 12px;
                margin-right: 10px;
            }
            form label{
                float:none !important;
                display: inline;
            }
            form div{
                padding:0px;
            }
            .exportbutton{
                background: url(../BulkUpload/images/export-to-excel-button-icon.png) no-repeat 8px 8px #316CA8;
                width:100px;    
            }
        </style>
        <script type="text/javascript">
            var loadingDivObj = (document.all);
            var ns4 = document.layers;
            var ns6 = document.getElementById && !document.all;
            var ie4 = document.all;
            if (ns4) {
                loadingDivObj = document.loadingDiv;
            } else if (ns6) {
                loadingDivObj = document.getElementById("loadingDiv").style;
            } else if (ie4) {
                loadingDivObj = document.all.loadingDiv.style;
            }

           stopLoading();

            function stopLoading() {
                if (ns4) {
                    loadingDivObj.visibility = "hidden";
                }
                else if (ns6 || ie4)
                    loadingDivObj.display = "none";
            }

            function startLoading() {
                if (ns4) {
                    loadingDivObj.visibility = "visible";
                }
                else if (ns6 || ie4)
                    loadingDivObj.display = "block";
            }
            $(document).ready(function() {

            });
            function exportBtn(){
                var rsh = document.getElementById("rsh").value;
                var year = document.getElementById("selYear").value;
                var bdm_id = document.getElementById("bdmId").value;
                $('#getDetails').attr("action", "exportDetails.htm");
                $('#getDetails').attr("action", "exportDetails.htm?rsh="+rsh+"&year="+year+"&bdm_id="+bdm_id);
                document.getDetails.submit();
            }
            
            function getSalesDetails(){
                $('#getDetails').attr("action", "salesRevenueUnitView.htm");
                document.getDetails.submit();
            }
        </script>
    </head>
    <body>
        <div id="content">
            <div class="container_inner">
                <div class="page_heading">
                    <c:if test="${rsh=='1'}">
                        RSH View
                    </c:if>
                    <c:if test="${rsh=='0'}">
                        Sales View
                    </c:if>
                    <c:if test="${rsh=='2'}">
                        Delivery View
                    </c:if>
                </div>
            </div>
            <div id="header">
                <form name="getDetails" id="getDetails" method="post" enctype="multipart/form-data" action="">
                    <input type="hidden" id ="rsh" value ="${rsh}" />
                    <label id="label">Financial Year: </label>
                    <select name="seled_Year" id="selYear" size="1" style="width:10%">
                        <option align="center" value="">-- Year --</option>
                        <c:forEach items="${yearsList}" var="yearsList" varStatus="sbuitr">
                            <option value="${yearsList}" ${filterData.financial_year == yearsList ? 'selected':''}>${yearsList}</option>
                        </c:forEach>
                    </select>  
                    <c:if test="${rsh=='1' || rsh=='2'}">
                        <label id="label">BDM: </label>
                        <select name="selected_bdm_id" id="bdmId" size="1" style="width:20%">
                            <option align="center" value="">-- Select BDM --</option>
                            <c:forEach items="${bdm_list}" var="bdm_list" varStatus="sbuitr">
                                <option value="${bdm_list.bdm_id}" ${bdm_list.bdm_id == filterData.bdm_id ? 'selected':''}>${bdm_list.bdm_name}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                    <input type="button" class="gobutton" id="go" name="submit_buton"  value="Go" onClick="getSalesDetails();"/>
                    <input type="button" class="exportbutton" name="excelExport" id="excelExport" onclick="exportBtn();" value="Export"/>
                </form>
            </div>
            <!--<form name="saveDetails" id="saveDetails" method="post" action="#">-->
                <div id="tabledetails">
                    <table id="tab">
                        <tr>
                            <c:if test="${rsh=='1' || rsh=='2'}">
                                <th rowspan="2">BDM</th>
                            </c:if>
                            <th rowspan="2" style="width: 50px;">Customer Group</th>
                            <th rowspan="2" style="width: 50px;">Unit</th>
                            <th rowspan="2" style="width: 150px;">Customer Name</th>
                            <th colspan="3" style="width: 150px;">Q1</th>
                            <th colspan="3" style="width: 150px;">Q2</th>
                            <th colspan="3" style="width: 150px;">Q3</th>
                            <th colspan="3" style="width: 150px;">Q4</th>
                            <th rowspan="2" style="width: 50px;">FY</th>
                        </tr>
                        <tr>
                            <th style="width: 50px;">APR</th>
                            <th style="width: 50px;">MAY</th>
                            <th style="width: 50px;">JUN</th>
                            <th style="width: 50px;">JUL</th>
                            <th style="width: 50px;">AUG</th>
                            <th style="width: 50px;">SEP</th>
                            <th style="width: 50px;">OCT</th>
                            <th style="width: 50px;">NOV</th>
                            <th style="width: 50px;">DEC</th>
                            <th style="width: 50px;">JAN</th>
                            <th style="width: 50px;">FEB</th>
                            <th style="width: 50px;">MAR</th>
                        </tr>
                        <c:choose>
                            <c:when test="${fn:length(details)!=0}">
                                <c:forEach items="${details}" var="details" varStatus="i">
                                    <c:choose>
                                        <c:when test="${(i.count) % 2 == 0}">
                                            <tr class="even">
                                        </c:when>
                                        <c:otherwise>
                                            <tr class="odd">
                                        </c:otherwise>
                                    </c:choose>
                                        <c:if test="${rsh=='1' || rsh=='2'}">
                                            <td>
                                                ${details.bdm_name}
                                            </td>
                                        </c:if>
                                        <td>
                                            ${details.group_name}
                                        </td>
                                        <td>
                                            ${details.unit_name}
                                        </td>
                                        <td>
                                            ${details.customer_name}
                                        </td>
                                        <td>
                                            ${details.april}
                                        </td>
                                        <td>
                                            ${details.may}
                                        </td>
                                        <td>
                                            ${details.june}
                                        </td>
                                        <td>
                                            ${details.july}
                                        </td>
                                        <td>
                                            ${details.august}
                                        </td>
                                        <td>
                                            ${details.september}
                                        </td>
                                        <td>
                                            ${details.october}
                                        </td>
                                        <td>
                                            ${details.november}
                                        </td>
                                        <td>
                                            ${details.december}
                                        </td>
                                        <td>
                                            ${details.january}
                                        </td>
                                        <td>
                                            ${details.february}
                                        </td>
                                        <td>
                                            ${details.march}
                                        </td>
                                        <td>
                                            ${details.total}
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${fn:length(details)==0}">
                                    <tr class="odd">
                                        <td colspan="19" style="font-weight: bold;">No data to display</td>
                                    </tr>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </table>
                    
                </div>
            <!--</form>-->
            
        </div>
    </body>
</html>

