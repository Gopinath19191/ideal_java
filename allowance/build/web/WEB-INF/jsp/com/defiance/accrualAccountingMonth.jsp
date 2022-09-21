<%-- 
    Document   : accrualAccountingMonth
    Created on : 12 Jul, 2021, 12:32:13 PM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <style>
            .gobutton{
                color:white;
                float:none;
                width:40px;
                cursor: pointer;
            }
            .updatebutton{
                background: none repeat scroll 0 0 #316ca8;
                border: 1px solid #BCCFEA; 
                font-weight: bold;
                height: 22px;
                margin: 5px;
                color:white;
                cursor: pointer;
            }
            #end_no{
                width: 100px;
            }
        </style>
        <script>
            $(function() {
                $('.datePick').datepicker({
                    changeMonth: true,
                    changeYear: true,
                    maxDate: new Date,
                    dateFormat:'dd-mm-yy'
                });
            });
//            $("#update").click(function(){
//                alert("in button");
//                
//            });
            function search(){
              $('#searchPage').attr("action", "accrualAccountingMonth.htm");
              return true
            };
            function isNumber(evt) {
                evt = (evt) ? evt : window.event;
                var charCode = (evt.which) ? evt.which : evt.keyCode;
                if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                    return false;
                }
                return true;
            }
            function updateAccrualClose() {
                var end_no = $("#end_no").val();
                var accounting_month = $("#acc_month").val();
                var accounting_year = $("#acc_year").val();
                if(end_no=='' || end_no == 'null'){
                    $("#end_no").css("border","1px solid #ff0000");
                    return false;
                }else{
                    $('#saveDetails').attr("action", "updateAccrualClose.htm?end_no="+end_no+"&month="+accounting_month+"&year="+accounting_year);
                }
            };
        </script>
    </head>
    <body>
        <div align="center"><font color="green" size="3">${success_msg}</font></div>
        <div class="container_inner">
            <div class="page_heading">
                Accrual Accounting Month
            </div>
        </div>
        <div class="searchBox">
            <form action="#" name="searchPage" method="post" id="searchPage">
                <label id="label" font-we>Select Year: </label>
                <select id="customer" name="year" style="width:10%">
                    <option value="">-- Year --</option>
                    <c:forEach items="${yearsList}" var="yearsList" varStatus="i">
                        <option value="${(yearsList.key)}" ${(selected_year==yearsList.key) ? 'selected':''}>${yearsList.value}</option>
                    </c:forEach>
                </select>
                <input type="submit" class="gobutton" id="go" name="submit"  value="Go" onchange="return search();"/>
            </form>
        </div>
        <div class="contentwrap" style="height:auto;">
            <table class="tableStructure">
                <tr class="headerCenter">
                    <th>Accounting Year</th>
                    <th>Accounting Month</th>
                    <th>Accrual Start No</th>
                    <th>Accrual End No</th>
                    <th>Closed On</th>
                </tr>
                <c:forEach items="${accrual_details}" var="data" varStatus="dataStatus">
                    <tr class="${dataStatus.count%2==0?'altrow':''}">
                        <td valign ="top" align="center">
                            ${selected_year}
                        </td>
                        <td valign ="top" align="center">
                            ${data.month}
                        </td>
                        <td valign ="top" align="center">
                            ${data.startDate}
                        </td>
                        <td valign ="top" align="center">
                            <c:if test="${data.endDate == '' || data.endDate == null}">
                                <input type="text" name="endDate" id="end_no" value ="" onkeypress="return isNumber(event)"/>
                                <input type="hidden" name="month" id="acc_month" value ="${data.startMonth}"/>
                                <input type="hidden" name="year" id="acc_year" value ="${selected_year}"/>
                            </c:if>
                            <c:if test="${data.endDate != '' && data.endDate != null}">
                                ${data.endDate}
                            </c:if>
                        </td>
                        <td valign ="top" align="center">
                            ${data.cut_off_date}
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty(accrual_details)}">
                    <tr>
                        <td colspan="5" align="center">
                            <font color="red">No Records Found</font>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${!empty(accrual_details)}">
                    <tr>
                        <td colspan="5" align="center">
                            <form name="saveDetails" id="saveDetails" method="post" action="#">
                                <input type="submit" class="updatebutton" id="update" name="submit"  value="Close Accounting Month Accrual" onclick="return updateAccrualClose();"/>
                            </form>
                            
                        </td>
                    </tr>   
                </c:if>
            </table>
        </div>
    </body>
</html>