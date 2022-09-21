<%-- 
    Document   : home
    Created on : Nov 11, 2010, 4:49:22 PM
    Author     : HARIHARAN.C
--%>
<%@include file="header.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
    <head>
        <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/QPD_home.css" type="text/css"/>--%>
        <title>iDeal Annual appraisal Home page </title>
        <script type="text/javascript">
            function noBack(){window.history.forward()}
            noBack();
            window.onload=noBack;
            window.onpageshow=function(evt){if(evt.persisted)noBack()}
            window.onunload=function(){void(0)}

var clickFlag = true;
$(document).ready(function(){
        $("#dialog:ui-dialog").dialog("destroy");
        $("#dialog-confirm").dialog({
                resizable: false,
                modal: true,
                autoOpen: false,
                buttons:{
                    "Yes": function() {
                            $("#managerDialog").val("1");
                            $(this).dialog("close");
                            $("#formSubmitButton").click();
                        },
                    "No": function() {
                            $("#managerDialog").val("0");
                            $(this).dialog("close");
                            $("#formSubmitButton").click();
                    }
                },
               show: 'slide',
               hide: 'slide'
            });
});


function getManagerConfirmation(){
        if($("#managerDialog").val()==""){
            $("#dialog-confirm").dialog("open");
            return false;
        }else{
            return true;
        }
}
function changeClass(val){
    $(".ticket_tab").removeClass("active_tab");
    $(val).addClass("active_tab");
}
         </script>
        <style type="text/css">
            h1{
	padding-bottom:5px;
	font-family:Arial, Helvetica, sans-serif;
	font-size:12px;
	color:#15428b;
	margin:0px;
	line-height:12px;
}
h2{
	font-family:Arial, Helvetica, sans-serif;
	font-size:12px;
	color:#666;
	margin:0px;
	line-height:12px;
	padding-bottom:3px;
	font-weight:bold;
}
h3{
	font-family:Arial, Helvetica, sans-serif;
	font-size:12px;
	color:#666;
	margin:0px;
	line-height:12px;
	padding-bottom:3px;
	font-weight:normal;
}
.img{
	position:relative;
	width:481px;
	height:251px;
	z-index:1;
	background-image:url(${pageContext.request.contextPath}/css/images/image3.jpg);
	margin: 10px auto 10px auto;
	background-repeat:no-repeat;
	}
       <%-- body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #ffffff;

}--%>
a{color:#333333;text-decoration:none;}
a:hover{color:#15428b;}
.contentwraphome{
	position:relative;
	width:96%;
	z-index:1;
	background-position:center;
	font-family:Arial, Helvetica, sans-serif;
        font-size:12px;
/*	margin-top: 30px;
	margin-left: 30px;
	margin-bottom: 30px;*/
        margin: 30px auto 30px auto;
	height: 531px;
        color:#666;
	border:#99bbe8 1px solid;
	background-color:#FFF;
}
 </style>
 </head>
 <body>
     <span class="topheading"  >Annual Appraisal</span>
     <div class="generalFormContent">
         <%@include file="menu.jsp" %>
         <form action="processDuration.do?qpage=true" method="POST" id="QPDDurationForm" name="QPDDurationForm">
             <div class="qpdSearch">
                 <table>
                     <tr>
                         <td>Select the Year for which you wish to process Appraisal</td>
                         <td>
                             <select name="appraisalYear" id="appraisalYear" class="required selectbox" style="width: 5em" >
                                 <c:forEach items="${yearData}" var="year">
                                     <c:choose>
                                         <c:when test="${appraisalYear == year}">
                                             <option value="${year}" selected="selected">${year}</option>
                                         </c:when>
                                         <c:otherwise>
                                             <option value="${year}">${year}</option>
                                         </c:otherwise>
                                     </c:choose>
                                 </c:forEach>
                             </select>
                         </td>
                         <td>
                             <input type="hidden" value="" name="managerDialog" id="managerDialog">
                             <input type="submit" id="formSubmitButton" value="Go" class="toolfind" />
                             <%--<input type="submit" id="formSubmitButton" value="Select" class="submitbutton" />--%>
                         </td>
                     </tr>
                 </table>
             </div>
             <div class="home1">
                 <h1>Purpose:</h1>
                 <h2>Annual Appraisal aims at:</h2>
                 <h3>Measuring the performance yearly basis and determining the performance payout.</h3>
                 <p>1.SELF EVALUATION (by Appraisee)</p>
                 <ul>
                     <li>Please Add KRAs in the relevant boxes.</li>
                     <li>KRAs have been identified separately for Bands A&L AND M&E.</li>
                     <li>Enter your achievements during the previous year</li>
                     <li>Enter the "Developmental needs" and "Goals" for the year.</li>
                 </ul>
                 <p>2.EVALUATION (by Appraiser)</p>
                 <ul>
                     <li>Appraiser has to key in their comments adjacent to the achievements,goals,Kra's and developmental needs</li>
                     <li>Ratings will be provided on the basis of their performance.</li>
                     <li>Final Ratings and promotion recommendation will be done by appraisers.</li>
                 </ul>
                 <p>3.OVERALL RATING</p>
                 <ul>
                     <li>There are three levels of review.</li>
                     <li>The ratings will be finalised by final reviewer and taken over by HR for further processing</li>
                 </ul>
             </div>
             <div class="home2">
                 <table border="1">
                     <tr>
                         <th>Performance Levels</th>
                         <th>Broad Guidelines & Measurement criteria</th>
                     </tr>
                     <tr>
                         <td style="text-align: center">5</td>
                         <td>Performance/effectiveness exceeds the standards and requirements of the task. Performance goes far beyond assigned task.</td>
                     </tr>
                     <tr>
                         <td style="text-align: center">4</td>
                         <td>Exceeds requirements of the assigned task significantly and consistently.</td>
                     </tr>
                     <tr>
                         <td style="text-align: center">3</td>
                         <td>Effectively and efficiently meets what is expected. Exceeds requirements at times.</td>
                     </tr>
                     <tr>
                         <td style="text-align: center">2</td>
                         <td>Meets what is expected, but at times off schedule and/or off quality. Requires guidance to perform efficiently</td>
                     </tr>
                     <tr>
                         <td style="text-align: center">1</td>
                         <td>Must improve. Does not meet basic requirements of the task. Not able to perform at required performance levels.</td>
                     </tr>
                 </table>
             </div>
         </form>
     </div>



      
               <%-- <b><netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/admin/begin.do" value="Appraisal Trigger Screen" /></b>
                <b><netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/admin/change.do" value="Change Appraisee Data" /></b>--%>
              <%-- <div class="tabletools submenuwrap">
               <%@include file="menu.jsp" %>
               <div class="contentwraphome">
                   <div class="contentBand">Defiance - Quarterly Performance Dialogue (QPD) </div>
                   <div class="contentBand">Defiance - Annual Appraisal</div>
                   <form action="processDuration.do?qpage=true" method="POST" id="QPDDurationForm" name="QPDDurationForm">
                       <table style="margin-top:10px;margin-left:16px;" align="center">
                       <tr>
                           <td><h1>Select the Quarter for which you wish to process QPD</h1></td>
                           <td><h1>Select the Year for which you wish to process Appraisal </h1></td>
                           <td>
                               <select name="appraisalQuarter" id="appraisalPeriod" class="required" style="width: 4em">
                                        <c:forEach begin="1" end="4" var="qValue">
                                              <c:choose>
                                                <c:when test="${appraisalQuarter == qValue}">
                                                    <option value="${qValue}" selected="selected">Q${qValue}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${qValue}">Q${qValue}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                </select>
                           </td>
                           <td>
                            <select name="appraisalYear" id="appraisalYear" class="required" style="width: 5em" >
                                    <c:forEach items="${yearData}" var="year">
                                               <c:choose>
                                                <c:when test="${appraisalYear == year}">
                                                    <option value="${year}" selected="selected">${year}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${year}">${year}</option>
                                                </c:otherwise>
                                            </c:choose>
                                    </c:forEach>
                                </select>
                           </td>
                           <td>
                               <input type="hidden" value="" name="managerDialog" id="managerDialog">
                               <input type="submit" id="formSubmitButton" value="Select" class="submitbutton" ${(surveyCheck==null || surveyCheck=="")?'onclick="return getManagerConfirmation()"':'' }   />
                               <input type="submit" id="formSubmitButton" value="Select" class="submitbutton" />
                           </td>
                       </tr>
                   </table>
               </form>
                   <div class="home1">
                       <div class="home1inner">
                           <h1>Purpose:</h1>
                           <h2>Annual Appraisal aims at:</h2>
                           <h3>Measuring the performance during the quarter and determining the quarterly performance payout.</h3>
                           <h2>Frequency:</h2>
                       </div>
                       <div class="home2inner"><img src="${pageContext.request.contextPath}/css/images/image1.jpg" width="303" height="182" align="middle" /></div>
                       <div class="home3inner"><h1>Quarterly Performance Dialogue Process</h1><img src="${pageContext.request.contextPath}/css/images/image2.jpg" width="536" height="244" />
                       <div class="home3inner"><h1>Annual Appraisal Process</h1><img src="${pageContext.request.contextPath}/css/images/image2.jpg" width="536" height="244" />
                       </div>
                   </div>
                   <div class="home2"><h1>Performance Levels and Performance Payout:</h1>
                       <h3>• Assessment is made on the extent to which the specified performance criteria have been met and outputs achieved. <br />
                           • Each KRA is assigned a weightage and the performance level is measured on a on a Five-point rating scale<br />
                           • Final Rating will be used to determine the Quarterly Performance Pay </h3>
                       <div class="img"></div>
                       <h1>Performance Pay:</h1>
                       <h3 style="margin-top:-10px">
                          <ul>
                            <li>Performance pay will be paid in 4 equated quarterly payouts of 25% each based on performance on the various Key Result Areas</li>
                            <li>Performance Payout for the quarter will take place along with the ensuing month’s salary</li> 
                           </ul>
                      </h3>
                      <h1 style="margin-top:-10px">Who are eligible?</h1>
                       <h3 style="margin-top:-10px">
                        <ul>
                         <li>All permanent employees on India payroll with a variable component as a part of their salary structure.</li>
                         <li>Employees who are deputed to projects at overseas locations and continue to have a quarterly variable compensation as a part of their salary structure.</li>
                         <li>Employees who are active on the rolls of the company on the last date of the quarter are eligible to receive variable compensation payout for the quarter under review.</li>
                        </ul>
                      </h3> 
                   </div>
                   <div class="home1">
                   <h1>Purpose:</h1>
                           <h2>Annual Appraisal aims at:</h2>
                        <h3>Measuring the performance yearly basis and determining the performance payout.</h3>
                   <p>1.SELF EVALUATION (by Appraisee)</p>
                   <ul>
                       <li>Please Add KRAs in the relevant boxes.</li>
                       <li>KRAs have been identified separately for Bands A&L AND M&E.</li>
                       <li>Enter your achievements during the previous year</li>
                       <li>Enter the "Developmental needs" and "Goals" for the year.</li>
                   </ul>
                   <p>2.EVALUATION (by Appraiser)</p>
                   <ul>
                       <li>Appraiser has to key in their comments adjacent to the achievements,goals,Kra's and developmental needs</li>
                       <li>Ratings will be provided on the basis of their performance.</li>
                       <li>Final Ratings and promotion recommendation will be done by appraisers.</li>
                   </ul>
                    <p>3.OVERALL RATING</p>
                   <ul>
                       <li>There are three levels of review.</li>
                       <li>The ratings will be finalised by final reviewer and taken over by HR for further processing</li>
                   </ul>
                    </div>
                   <div class="home2">
                       <table border="1">
                           <tr>
                               <th>Performance Levels</th>
                               <th>Broad Guidelines & Measurement criteria</th>
                           </tr>
                           <tr>
                               <td style="text-align: center">5</td>
                               <td>Performance/effectiveness exceeds the standards and requirements of the task. Performance goes far beyond assigned task.</td>
                           </tr>
                           <tr>
                               <td style="text-align: center">4</td>
                               <td>Exceeds requirements of the assigned task significantly and consistently.</td>
                           </tr>
                           <tr>
                               <td style="text-align: center">3</td>
                               <td>Effectively and efficiently meets what is expected. Exceeds requirements at times.</td>
                           </tr>
                           <tr>
                               <td style="text-align: center">2</td>
                               <td>Meets what is expected, but at times off schedule and/or off quality. Requires guidance to perform efficiently</td>
                           </tr>
                           <tr>
                               <td style="text-align: center">1</td>
                               <td>Must improve. Does not meet basic requirements of the task. Not able to perform at required performance levels.</td>
                           </tr>
                       </table>
                   </div>
               </div>
</div>--%>


            <%--<%@include file="footer.jsp" %>--%>
    </body>

    <div id="dialog-confirm" title="" style="display: none;">
        Did you and your reporting manager have a formal dialogue to discuss your performance during the last quarter?
    </div>
    <style type="text/css">
        .home2 table tr th{
        background-color: #A9BFD3;
        }
        .home2 table{
        border-collapse: collapse;
        margin-left: 10px;
        }
    </style>
    
</html>
