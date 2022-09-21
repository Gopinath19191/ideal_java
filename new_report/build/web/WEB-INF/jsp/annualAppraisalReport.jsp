<%--
    Document   : myjsp
    Created on : Apr 10, 2013, 10:17:36 AM
    Author     : 9000337
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@include file="header.jsp" %>
    <html>
        <head>
            <title>Annual Appraisal Report </title>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
			<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/js/filterOnChange.js"></script>
        </head>
        <style type="text/css">
            #loadingDiv img{ border: none; }
            #loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1}
            .paging {
                margin:10px 0 10px 18px;
                font-size : 11px;
            }

            .paging a {
                border: 1px solid #BBCFEA;
                padding: 3px 5px;
                color : #4884B8;
                text-decoration: none;
                font-weight: bold;
            }

            a.active {
                border: 1px solid #A4A5A5;
                color: #5A5B5B;
                font-weight: bold;
            }

            a.inactive {
                border: 1px solid #DEE8F4;
                color : #A3C5DE;
                font-weight: bold;
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

			$(document).ready(function() {
				$("#reportPage").validate();
			});
			function getExcelReport() {

				$('#reportPage').attr("action", "annualAppraisalExport.htm");
				if ($("#employee_search").val() == 'Search by Employee Number or First Name or Last name') {
					document.getElementById("employee_id").value = "";
				}
			if ($("#reviewer_search").val() == 'Search by Employee Number or First Name or Last name') {
					document.getElementById("reviewer_id").value = "";
				}
			if ($("#normalizer_search").val() == 'Search by Employee Number or First Name or Last name') {
					document.getElementById("normalizer_id").value = "";
				}
				document.reportPage.submit();
				//}
			}
			function getFilterList() {

				$('#reportPage').attr("action", "annualAppraisalReport.htm");
				if ($("#employee_search").val() == 'Search by Employee Number or First Name or Last name') {
					document.getElementById("employee_id").value = "";
				}
			if ($("#reviewer_search").val() == 'Search by Employee Number or First Name or Last name') {
					document.getElementById("reviewer_id").value = "";
				}
			if ($("#normalizer_search").val() == 'Search by Employee Number or First Name or Last name') {
					document.getElementById("normalizer_id").value = "";
				}
				document.reportPage.submit();
			
				startLoading();
			}


			$(document).ready(function() {
				$("#employee_search").autocomplete("ajaxGetManager.htm", {
					minChars: 1,
					matchContains: true
				});
				$("#employee_search").result(function(event, data, formatted) {
					if (data) {
						$("#employee_id").val(data[1]);
					}
				});
			
			$("#reviewer_search").autocomplete("ajaxGetReviewer.htm", {
					minChars: 1,
					matchContains: true
				});
				$("#reviewer_search").result(function(event, data, formatted) {
					if (data) {
						$("#reviewer_id").val(data[1]);
					}
				});
			
			$("#normalizer_search").autocomplete("ajaxGetNormalizer.htm", {
					minChars: 1,
					matchContains: true
				});
				$("#normalizer_search").result(function(event, data, formatted) {
					if (data) {
						$("#normalizer_id").val(data[1]);
					}
				});

			});
                        function setSbuVal(Value)
                        {
                            $.ajax(
                            {
                                url:"getSubPracticeGroup.htm?id="+Value,
                                dataType: "html",
                                success:function(data){
                                    $('#subSbu').length=0;
                                    $('#subSbu').html(data);
                                }
                            });
                        }

        </script>

        <body>
            <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
            <div id="container">
                <div class="container_inner">
                    <div class="page_heading">
                        Annual Appraisal Report
                    </div>
                </div>
                <div class="tabletools">
                    <form action="#" name="reportPage" method="post" id="reportPage">
                        <table id="searchForm" style="width: 100%;table-layout: fixed">
                            <tbody>
                                <input type="hidden" id="page" name="page" value="${paging[0] > 1 ? paging[1]:'1'}" />
                                    <tr>
                                        <td style="width: 33.33% !important">
                                            <label for="appraiser_search" style="display: inline-block;width: auto;"><b>Appraiser</b></label>
                                            <input type="text" id="employee_search" name="employee_search" style="width:200px;float: right;margin-right: 15px;color: #666666;" value="${result.employee_id != '' || result.employee_id != null?result.employee_search:'Search by Employee Number or First Name or Last name'}" onfocus="if (this.value == 'Search by Employee Number or First Name or Last name')
                                                    this.value = '';" onblur="if (this.value == '')
                                                    this.value = 'Search by Employee Number or First Name or Last name';" />
                                            <input type="hidden" id="employee_id" name="employee_id" value = "${result.employee_id}" />
                                        </td>
                                        <td style="width: 33.33% !important;">
                                            <label for="reviewer_search" style="display: inline-block;width: auto;"><b>Reviewer</b></label>
                                            <input type="text" id="reviewer_search" name="reviewer_search" style="width:200px;float: right;margin-right: 15px;color: #666666;" value="${result.reviewer_id != '' || result.reviewer_id != null?result.reviewer_search:'Search by Employee Number or First Name or Last name'}" onfocus="if (this.value == 'Search by Employee Number or First Name or Last name')
                                                    this.value = '';" onblur="if (this.value == '')
                                                    this.value = 'Search by Employee Number or First Name or Last name';" />
                                            <input type="hidden" id="reviewer_id" name="reviewer_id" value = "${result.reviewer_id}" />
                                        </td>
                                        <td style="padding-left: 20px">
                                            <label for="normalizer_search" style="display: inline-block;width: auto;"><b>Normalizer</b></label>
                                            <input type="text" id="normalizer_search" name="normalizer_search" style="width:200px;float: right;margin-right: 15px;color: #666666;" value="${result.normalizer_id != '' || result.normalizer_id != null?result.normalizer_search:'Search by Employee Number or First Name or Last name'}" onfocus="if (this.value == 'Search by Employee Number or First Name or Last name')
                                                    this.value = '';" onblur="if (this.value == '')
                                                    this.value = 'Search by Employee Number or First Name or Last name';" />
                                            <input type="hidden" id="normalizer_id" name="normalizer_id" value = "${result.normalizer_id}" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label for="sbu" style="width: 60px"><b>SBU</b></label>
                                            <select name="sbu" id="sbu" class="selectbox textbox_new" style="width: 205px;float: right;margin-right: 15px;" onchange="setSbuVal(this.value);">
                                            <option  value="">--Select--</option>
                                                <c:forEach items="${sbu}" var="sbu" varStatus="i">
                                                        <option  value="${sbu.id}" ${sbu.id eq result.sbu ? 'selected="selected"':''}>${sbu.name}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <label for="subSbu" style="width: 60px"><b>Sub SBU</b></label>
                                             <select name="subSbu" id="subSbu" class="selectbox"  style="width: 205px;float: right;margin-right: 15px;">
                                                <option  value="All">--Select--</option>
                                                    <c:forEach items="${subSbu}" var="subSbu" varStatus="i">
                                                    <option  value="${subSbu.id}" ${subSbu.id eq result.subSbu ? 'selected="selected"':''}>${subSbu.name}</option>
                                                    </c:forEach>
                                            </select>
                                        </td>
                                        <td style="padding-left: 20px">
                                            <label for="appraisalYear" style="width: 60px"><b>Year</b></label>
                                            <select name="appraisalYear" id="appraisalYear" class="selectbox"  style="width: 205px;float: right;margin-right: 15px;">
                                                <option  value="">--Select--</option>
                                                <c:forEach items="${AppraisalYearList}" var="year" varStatus="i">
                                                        <option  value="${year.appraisalYear}" ${year.appraisalYear eq result.appraisalYear ? 'selected="selected"':''}>${year.appraisalYear}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                        </td>
                                        <td>
                                        </td>
                                        <td style="text-align: right; padding-right: 15px; ">
                                            <input name="go" class="gobutton" onclick="getFilterList()" type="submit" value="Go"/>
                                            <input class="exportbutton" type="button" style="margin-left: 20px;" onclick="getExcelReport()" value="Export"/>
                                        </td>
                                    </tr>

                            </tbody>
                        </table>
                    </form>
                </div>   
                <div id="datadisplay" style="width: 100% !important;">
                    <table style="font-size: 10px;width: 100%;table-layout: fixed;">
                        <tbody>
                            <th>Appraise Number</th>
                            <th>Appraise Name</th>
                            <th>Practice Group</th>
                            <th>Sub Practice Group</th>
                            <th>Status</th>
                            <th>Appraiser Number</th>
                            <th>Appraiser Name</th>
                            <th>Reviewer Number</th>
                            <th>Reviewer Name</th>
                            <th>Normalizer Number</th>
                            <th>Normalizer Name</th>
                            <th>Appraiser Rating</th>
                            <th>Reviewer Rating</th>
                            <th>Normalized Reviewer Rating</th>
                        </tbody>
                        <tbody>
                            <c:forEach items="${AnnualAppraisalReport}" var="item">
                                <tr>
                                    <td style="word-wrap: break-word;">${item.appraiseeNumber}</td>
                                    <td style="word-wrap: break-word;">${item.appraiseeName}</td>
                                    <td style="word-wrap: break-word;">${item.practiceGroup}</td>
                                    <td style="word-wrap: break-word;">${item.subPracticeGroup}</td>
                                    <td style="word-wrap: break-word;">${item.submittedStatus}</td>
                                    <td style="word-wrap: break-word;">${item.appriaserNumber}</td>
                                    <td style="word-wrap: break-word;">${item.appraiserName}</td>
                                    <td style="word-wrap: break-word;">${item.reviewerNumber}</td>
                                    <td style="word-wrap: break-word;">${item.reviewerName}</td>
                                    <td style="word-wrap: break-word;">${item.normalizerNumber}</td>
                                    <td style="word-wrap: break-word;">${item.normalizerName}</td>
                                    <td style="word-wrap: break-word;">${item.appraiserRating}</td>
                                    <td style="word-wrap: break-word;">${item.reviewerRating}</td>
                                    <td style="word-wrap: break-word;">${item.normalisedReviewerRating}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <cn:if test="${paging[0] > 1}">
                        <%@include file="/WEB-INF/jsp/paging.jsp" %>
                    </cn:if>
                </div>
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
			function loadForm(page) {
                            // alert("In side Load form "+page);
                            $('#page').val(page);
                            $('#reportPage').attr("action", "annualAppraisalReport.htm");
                            document.reportPage.submit();
			}
                </script>
            </div>
        </body>
    </html>