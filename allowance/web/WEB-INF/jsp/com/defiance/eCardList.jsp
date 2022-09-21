<%-- 
    Document   : eCardList
    Created on : 18 Jan, 2022, 12:27:04 PM
    Author     : 16221
--%>
<%-- 
    Document   : officeTimingUpdate
    Created on : 23 May, 2020, 5:49:11 PM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <title>Office Timing Selection</title>
        <style>
            table,td{
                border:1px solid #CEDFF1;
                border-collapse:collapse;
                text-align:center;
                color:#666666;
            }
            
            #tabledetails th, #dataDetails th{
                background: url(images/table-header-strip.jpg) repeat-x scroll top center #EFF4FB;
                font-weight: bolder;
                font-size: 11px;
                padding: 5px 10px; 
                padding-top: 3px;
                border: 1px solid #CEDFF1;
            }
            #tabledetails table tr td, #dataDetails table tr td{
                background: none repeat scroll 0 0;
                border: 1px solid #CEDFF1;
                padding: 8px;
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
            
            #label{
                font-weight: bold;
                margin-left: 5px;
                margin-right: 5px;
                color:#666666;
            }
            #content{
                border:1px ;
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
            p{
                margin:0px;
                padding:0px;
            }
            .successMessage{
                text-align: center;
                font-weight: bold;
                color:green;
                font-size: medium;

            }
            .notice_page{
                margin-top: 10px;
            }
            .goToList {
                color: #4C83B3;
                float: right;
                font-size: 12px;
                font-weight: bold;
                margin: 20px 40px 10px;
            }
            .page_heading {
                color: #666666;
                font-size: 18px;
                font-weight: bold;
                display: inline-block;
                width: 35%;
                padding: 25px 0px 0px 0px;
                margin: 0px 0px 10px 0px;
            }
        </style>
        <script>

        </script>
    </head>
    <body>
        <div id="content">
            <div id="dataDetails">
                <c:if test="${list_type=='0'}">
                    <div class="page_heading" style="margin-bottom:10px;">
                        "Pick Me" Card Distributed Details
                    </div>
                </c:if>
                <c:if test="${list_type=='1'}">
                    <div class="page_heading" style="margin-bottom:10px;">
                        "Pick Me" Card Received Details
                    </div>
                </c:if>
                <div class="goToList">
                    <img src="/allowance/images/add.png" title="List Procurement" alt="List Procurement">
                    <a style="text-decoration:none;color: #4C83B3;" target="_self" href="eCardPage.htm">Given Card</a>
                </div>
                <table id="tab">
                    <tr>
                        <c:if test="${list_type=='0'}">
                            <th style="width: 200px;">Given To</th>
                        </c:if>
                        <c:if test="${list_type=='1'}">
                            <th style="width: 200px;">Received From</th>
                        </c:if>
                        <th style="width: 150px;">Card Type</th>
                        <c:if test="${list_type=='0'}">
                            <th style="width: 50px;">Give On</th>
                        </c:if>
                        <c:if test="${list_type=='1'}">
                            <th style="width: 50px;">Received On</th>
                        </c:if>
                        
                        <th style="width: 150px;">Card</th>
                        <th style="width: 50px;">View</th>
                    </tr>
                    <c:choose>
                        <c:when test="${fn:length(GivenCardList)!=0}">
                            <c:forEach items="${GivenCardList}" var="details" varStatus="i">
                                <c:choose>
                                    <c:when test="${(i.count) % 2 == 0}">
                                        <tr class="even">
                                        </c:when>
                                        <c:otherwise>
                                        <tr class="odd">
                                        </c:otherwise>
                                    </c:choose>
                                    <td>
                                        ${details.employee_name}
                                    </td>
                                    <td>
                                         ${details.card_name}
                                    </td>
                                    <td>
                                        ${details.given_on}
                                    </td>
                                    <td>
                                        <a href="attachmentDownload.htm?fileName=${details.file_name}&fileType=jpg" target="_blank" name="file" id="attachedFileValue">${details.card_name}</a>
                                    </td>
                                    <td>
                                        <a title="View" target="_self" style="text-decoration:none" href="${pageContext.request.contextPath}/getEcardDetails.htm?ecard_id=${details.card_id}&mycard=${list_type}"><img src="${pageContext.request.contextPath}/images/eye.png" style="padding-left: 8px;"></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${fn:length(GivenCardList)==0}">
                                <tr class="odd">
                                    <td colspan="4" style="font-weight: bold;">No data to display</td>
                                </tr>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </table>
            </div>
        </div>
    </body>
</html>

