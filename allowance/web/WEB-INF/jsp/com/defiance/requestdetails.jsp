<%-- 
    Document   : requestdetails
    Created on : May 30, 2012, 12:13:04 PM
    Author     : 14578
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <script type="text/javascript">
            function requsetStatus(status)
            {
                var reason = document.getElementById("rejectReason").value;
                if(status=="a" || status=="c")
                {
                    $("#approvebutton").attr("disabled","true");
                    $("#rejectbutton").attr("disabled","true");
                    $("#cancelledbutton").attr("disabled","true");
                    $('#individualdet').attr("action", "approveRequest.htm?list=pending&statusType="+status);
                    document.individualdet.submit();
                }
                else if(status=="r")
                {
                    if(reason!=''){
                        $("#approvebutton").attr("disabled","true");
                        $("#rejectbutton").attr("disabled","true");
                        $("#cancelledbutton").attr("disabled","true");
                        $('#individualdet').attr("action", "approveRequest.htm?list=pending&statusType="+status);
                        document.individualdet.submit();
                       
                    }else{
                        alert("Enter reason for reject");}
                }
            }
           
        </script>
    </head>
    <body>
        <div class="container_inner">
            <div class="page_heading">
                Holiday Allowance
            </div>
             <div class="tabletools">
                <a class="menuAdd" title="Pending List"   href="approve.htm?list=pending&page=1" target="_self" style="text-decoration:none;">List Pending Request</a>
            </div><br/><br/>
        </div>
        <div class="contentwrap" style="height:auto;">
            <div class="commonformheader">Approve / Reject Holiday Allowance</div>
            <form id="individualdet" name="individualdet" action="" method="POST">
            <table width="100%" cellspacing="5">
                <tr>
                    <td style="padding-left:50px;" width="30%"><label class="pmLabeld">Employee Name</label></td>
                    <td align="left"><input type="text" disabled id="empName" name="empName" style="width: 25%" value="${result.empName}"/></td>
                </tr>
                <tr>
                    <td style="padding-left:50px;"class="pmLabeld">Holiday Date</td>
                    <td align="left"><input type="text" disabled id="holidaydate" name="holidaydate" style="width: 25%" value="${result.allowance_date}"/></td>
                </tr>
                <tr>
                    <td style="padding-left:50px;"class="pmLabeld">Project Name</td>
                    <td align="left"><input type="text" disabled id="prjName" name="prjName" style="width: 25%" value="${result.prajectName}"/></td>
                </tr>
                <tr>
                    <td style="padding-left:50px;"class="pmLabeld">Total Hours:</td>
                    <td align="left">

                        <span style="width:50%" >
                            <select style="width:50px" id="selHr" name="selHr"  >
                                <c:forEach begin="0" end="12" var="hoursArr" >
                                    <c:choose><c:when test="${hoursArr <= 9 }"><c:set var="hoursArr" value="0${hoursArr}"></c:set></c:when><c:otherwise><c:set var="hoursArr" value="${hoursArr}"></c:set></c:otherwise></c:choose>
                                    <option ${(hoursArr == totalHr) ? 'selected':''} value="${hoursArr}">${hoursArr}</option>
                                </c:forEach>

                            </select> Hr <b>:</b>
                        </span>
                        <span style="width:50%" >
                            <select style="width:50px" id="selMin" name="selMin">
                                <c:forEach begin="0" end="60" var="minArr">
                                    <c:choose>
                                        <c:when test="${totalMin == minArr}">
                                            <c:choose><c:when test="${minArr <= 9 }"><c:set var="minArr" value="0${minArr}"></c:set></c:when><c:otherwise><c:set var="minArr" value="${minArr}"></c:set></c:otherwise></c:choose>
                                            <option selected value="${minArr}">${minArr}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose><c:when test="${minArr <= 9 }">0${minArr}<c:set var="minArr" value="0${minArr}"></c:set></c:when><c:otherwise><c:set var="minArr" value="${minArr}"></c:set></c:otherwise></c:choose>
                                            <option value="${minArr}">${minArr}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <input type="hidden" id="minsValue" name="minsValue" value="${totalMin}"/>
                            </select> Min
                        </span>
                    </td>
                </tr>
                <tr>
                    <td style="padding-left:50px;"class="pmLabeld" >Reason</td>
                    <td align="left"><textarea id="reason" name="reason" style="width: 25%" disabled>${result.reason}</textarea></td>
                </tr>
                <tr>
                    <td style="padding-left:50px;"class="pmLabeld">Reason For Rejection:</td>
                    <td align="left"><textarea id="rejectReason" style="width: 25%" class="required" name="rejectReason"></textarea></td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan="2"><input type="button" onclick="requsetStatus('a');" class="submitbutton"  id="approvebutton" name="approvebutton" value="Approve"/>
                        <input type="button"  onclick="requsetStatus('r');" class="rejectbutton" id="rejectbutton"  name="rejectbutton" value="Reject"/>
                        <input type="button" onclick="requsetStatus('c');" class="cancelbutton " id="cancelledbutton"  name="cancelledbutton" value="Cancel"/></td>
                </tr>
                <input type="hidden" readonly id="list" name="list" Value="pending"/>
                <input type="hidden" readonly id="requestId" name="requestId" Value="${primaryId}"/>
                <input type="hidden" readonly name="page" id="page" value="1"  />
                <input type="hidden" readonly name="totalHrs" id="totalHrs" value=""/>
                </table>
            </form>
        </div>
    </body>
</html>
