<%--
    Document   : address
    Created on : Mar 13, 2012, 12:12:20 PM
    Author     : 15065
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entrée de feuille de temps</title>
    </head>
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:1500px;z-index:40;background-color:rgba(0,0,0,0.5);display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 14%; left: 45%;text-align:center"><img src="${pageContext.request.contextPath}/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <input type="hidden" value="${pageContext.request.contextPath}" id="base_path" />
        <div align="center" style="margin:15px 0px -20px 0px;" id="successDiv"><font color="green" size="4">${success_msg}</font></div>
        <div class="container_inner">
            <div class="page_heading">
                Entrée de feuille de temps
            </div>
            <div class="notice_page">
                <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/images/icon_alert.png" /></div>
                <div style="padding-left:20px;">
                    <ul class="notice_page_ul">
                        <li>Sélectionnez les cases à cocher avant de soumettre votre feuille de temps. C’est seulement sur la base de cette sélection que votre feuille de temps sera soumise à validation.</li>
                        <li>Merci de saisir les régularisations sur des lignes séparées, en choisissant la raison appropriée. Si besoin, vous avez possibilité d’ajouter des lignes pour chaque jour en cliquant sur « + » dans la colonne action.</li>                                                                       
                        <li>Les heures de présence individuelle pour la journée en cours seront disponibles le jour suivant à 10h00 a.m. IST pour les employés travaillant en Inde. Toutefois la feuille de temps pourra être saisie et sauvegardée pour la journée en cours.</li>
                        <li>En cas d’heures de présence incorrectes et d’heures disponibles incorrectes, veuillez contacter l’assistance technique idealsupport@hindujatech.com</li>
                        <li>Appuyer sur Ctrl+F5 pour rafraichir la page si la feuille de temps n’est pas entièrement chargée.</li>
                    </ul>
                </div>
            </div>
        </div>
        <form method="post" name="tmisheetFilterPage" id="tmisheetFilterPage" action="#" onSubmit="">
            <input type="hidden" value="${formData.employeeId}" id="employee_id" name="employeeId" />
            <div class="searchBox">
                <table width="100%" border="0" >
                    <tr>
                        <td style="padding-left:10px;color:#666666;" width="10%"><b>${days}Choisissez l’année :</b></td>
                        <td  width="7%">
                            <select name="res_year" id="year" style="width:70px;" onChange="loadMonth(this.value)">
                                <c:forEach  items="${year_list}" var="yearList" >
                                    <option ${(yearList.key == currentYear)?'selected':''} value="${yearList.key}">${yearList.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="color:#666666;" width="10%"><b>Choisissez le mois :</b></td>
                        <td>
                            <select name="res_month" id="month" onChange="monthChange()">
                                <option value="">--Choisissez le mois--</option>
                                <c:forEach  items="${month_list}" var="monthList" >
                                    <option ${(monthList.key == currentMonth)?'selected':''} value="${monthList.key}">${monthList.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="color:#666666;" width="10%"><b>Choisissez le statut :</b></td>
                        <td>
                            <select name="res_status" id="status" onChange="statusChanged()">
                                <option value="">Être soumis</option>                                
                                <c:forEach  items="${status_list}" var="statusList" >
                                    <option ${(statusList.configuration_key == formData.status)?'selected':''} value="${statusList.configuration_key}">${statusList.configuration_value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <a onClick="refreshData('Index')" style=" padding: 7px 8px 8px 8px;background: #316CA8;width: 90px;height: 41px;font-family: Arial;font-weight: bold;font-size: 13px;color: #FFFFFF;text-align: center;border: 1px solid #4492BF;cursor: pointer;">Réinitialiser</a>
                            <input type="button" class="exportbutton" name="excelExport" id="excelExport" onclick="exportBtn();" value="Exporter"/>
                            <input type="button" class="exportpdfbutton" name="pdfExport" id="pdfExport" onclick="exportPdfBtn();" value="Exporter Pdf"/>
                        </td>
<!--                       <td>
                            <a href="userManual.htm"  target="_blank" style="text-decoration:none;color: #4C83B3; font-size: 11px;"><img src="/iDeal_timesheet/images/usermanual2.png"  alt="User Manual" style="width: 28px;height: auto;"></a>
                        </td>-->
                    </tr>
                </table>
            </div>
            <div class="contentwrap">
                <table width="100%" border="0">
                    <tbody>
                        <tr>
                            <td>
                                <div id="timesheet">
                                    <script type="text/javascript">
                                        $(function() {
                                            loadTimesheet_New(${currentMonth},${currentYear});
                                        });
                                        
                                        function statusChanged(){
                                            var year = $('#year').val();
                                            var month = $('#month').val();
                                            loadMonth(year);
                                            //
                                            var status = $('#status').val();
                                            $("#loadingDiv").show();
                                            setTimeout(function() {
                                                loadTimesheet_New(month,year,status);
                                            }, 100);
                                        }
                                        function monthChange(){
                                            var year = $('#year').val();
                                            var month = $('#month').val();
                                            var status = $('#status').val();
                                            $("#loadingDiv").show();
                                            setTimeout(function() {
                                                loadTimesheet_New(month,year,status);
                                            }, 100);
                                        }
                                    </script>

                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </form>
        <div id="checkSelect" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="checkSelectFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Choisissez la case à cocher, SVP<br></p><p class="plainButton" id="checkSelectYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="projectNotification" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="rprojectNotificationFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Choisissez le projet, SVP<br></p><p class="plainButton" id="projectNotificationYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="saveRejectNotification" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="saveRejectNotificationFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Les entrées renvoyées ne peuvent pas être sauvegardées<br>Veuillez soumettre, SVP<br></p><p class="plainButton" id="saveRejectYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="emptyEntryError" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="emptyEntryErrorFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Les heures de la feuille des temps ne devraient pas être vides<br></p><p class="plainButton" id="emptyEntryErrorYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="24hrsEntryError" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="24hrsEntryErrorFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Les heures de la feuille des temps ne peuvent pas dépasser 23<br></p><p class="plainButton" id="24hrsEntryErrorYes"style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="60MinEntryError" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="60MinEntryErrorFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Les minutes de la feuille des temps ne peuvent pas dépasser 59<br></p><p class="plainButton" id="60MinEntryErrorYes"style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="halfDayEntryError" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="halfDayEntryErrorFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Veuillez saisir des heures valides, SVP<br><br>Vous avez demandé des congés pour une demi-journée<br></p><p class="plainButton" id="halfDayEntryErrorYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="onceSubmitNotification" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="onceSubmitNotificationFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Veuillez soumettre tous les enregistrements de la journée en une seule fois"<br></p><p class="plainButton" id="onceSubmitNotificationYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="availableHrsNotification" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="availableHrsNotificationFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Veuillez soumettre tous les enregistrements de la journée en une seule fois</p><p class="plainButton" id="availableHrsNotificationYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="attendanceHrsNotification" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="attendanceHrsNotificationFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Veuillez saisir un nombre d’heures minimal égal au nombre d’heures de présence</p><p class="plainButton" id="attendanceHrsNotificationYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="regularizeHrsNotification" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="regularizeHrsNotificationFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Veuillez régulariser vos heures, SVP<br></p><p class="plainButton" id="regularizeHrsNotificationYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="regularizeReasonNotification" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="regularizeReasonNotificationFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Veuillez sélectionner la raison de votre régularisation<br></p><p class="plainButton" id="regularizeReasonNotificationYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="remarksEntryError" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="remarksEntryErrorFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Les remarques ne doivent pas être vides si vous avez choisi la raison de la régularisation</p><p class="plainButton" id="remarksEntryErrorYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="monthEntryError" style="position:absolute;width:100%;height:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="monthEntryErrorFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Veuillez choisir le mois à exporter, SVP.</p><p class="plainButton" id="monthEntryErrorYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="policyError" style="position:absolute;width:100%;height:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="policyErrorFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Vous avez dépassé la limite du 'travail à domicile' pour le Mois/Année<br>Please refer policy document. </p><p class="plainButton" id="policyErrorYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="policyNotification" style="position:absolute;width:100%;height:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="policyNotificationFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Message!</p>
                <div style="padding: 5px;">
                    <p style="padding: 10px 0px 0px 0px;margin:0px;text-align: center;">Work From Home Policy</p><br>
                    <p style="margin: 0px 10px;text-align: left;">Eligibilité pour travailler à domicile: <br><br>1 RM/PM/L’approbation du client est réquise<br>2 Ordinateur portable de HTL/Client<br>3 Les employés qui travaillent chez le client doivent suivre sa politique concernant le travail à domicile</p><p class="plainButton" id="policyNotificationYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
    </body>
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
        function stopLoading() {
        //    var loadingDivObj=(document.all);
        //    var ns4=document.layers;
        //    var ns6=document.getElementById&&!document.all;
        //    var ie4=document.all;
        //    if (ns4) {
        //        loadingDivObj.visibility = "hidden";
        //    }
        //    else if (ns6 || ie4)
        //        loadingDivObj.display = "none";
            setTimeout(function(){
                $("#loadingDiv").hide();
            },100);

        }

        function startLoading() {
        //    var loadingDivObj=(document.all);
        //    var ns4=document.layers;
        //    var ns6=document.getElementById&&!document.all;
        //    var ie4=document.all;
        //    if (ns4) {
        //        loadingDivObj.visibility = "visible";
        //    }
        //    else if (ns6 || ie4)
        //        loadingDivObj.display = "block";
            setTimeout(function(){
                $("#loadingDiv").show();
            },100);

        }
    </script>
</html>

