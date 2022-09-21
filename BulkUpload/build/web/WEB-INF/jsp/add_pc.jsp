<%-- 
    Document   : add_pc
    Created on : 6 Jul, 2017, 1:13:35 PM
    Author     : 16364
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="header.jsp"  %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Consultant </title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/filterOnChange.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autoSuggest.mod.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />        
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
        <link href="/ideal_revamp/favicon.ico" type="image/x-icon" rel="icon" />
        <link href="/ideal_revamp/favicon.ico" type="image/x-icon" rel="shortcut icon" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ext-all.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tp.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/extjs/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/extjs/ext-all.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jscript.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
        <style>
            #commonformbg{
                background: white;
            }
            #addFormTable{
                width:960px;
                padding:20px;
            }
            #addFormTable tr td{
                width:240px;
            }
            form label{
                width:160px;
                font-weight: bold;
            }
            input[type=text]{
                width:200px;
                height:20px;
                color: #000;
                border:1px solid #B5B8C8;
            }
            .employeeAddress{
                max-height: 60px;
                max-width: 200px;
                width:200px;
                padding:0px;
                border:1px solid #B5B8C8;
                height:20px;
            }
            legend{
                background: #fff;
                color: #4D85B8;
                font-size: 14px;
                font-weight: bold;
                margin: 10px 0px 5px 0px;
            }    
            select{
                width:202px;
                border:1px solid #B5B8C8;
                height:22px;
            }
            .listLink{
                float: right;
                color: #4C83B3;
                font-weight: bold;
                font-size: 12px;
                margin-right: 10px;
                padding-left: 2px;
            }
            .page_heading{
                margin-bottom: 15px;
            }
            .notice_page{
                background-color: #ECE6D2;
                background-repeat: no-repeat;
                border: 1px solid #DDD6B7;
                width: 1000px;
                height:30px;
                margin-bottom:10px;
            }
            .instruction{
                margin:8px 0px 0px 35px;
            }
            .submitbutton_TS, .cancelbutton_TS{
                border-radius: 5px;
                cursor: pointer;
            }
        </style>
    
    <script type="text/javascript">
        $(function() {
	$('#practiceGroup').change(function() {
        $.ajax({
            url: "getSubPracticeGroups.htm?id=" + $(this).val(),
            dataType: "html",
            success: function(data) {
                $('#subPracticeGroup').html(data);
                setToolTipForSelects();
            }
        });
    });
    $('#countryId').change(function() {
        $.ajax({
            url: "getRegions.htm?id=" + $(this).val(),
            dataType: "html",
            success: function(data) {
                $('#regionId').html(data);
                $('#regionId').change();
            }
        });
    });
    $('#regionId').change(function() {
        $.ajax({
            url: "getCities.htm?id=" + $('#countryId').val() + "&subId=" + $(this).val(),
            dataType: "html",
            success: function(data) {
                $('#cityId').html(data);
            }
        });
    });
 });
 
 function setToolTipForSelects() {
    $('select option').each(function() {
        $(this).attr("title", $(this).text());
    });
}
        $(document).ready(function() {
            $("#startdate,#enddate").keydown(function() { 
                    return false;
                });
                
                var aa=$("#startdate").val();
                if(aa!=''){
                    aa = aa.split("-");
                  $("#enddate").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true, changeYear: true,maxDate: '0'});
                  $("#enddate").datepicker("option", "minDate", new Date(aa[2], --aa[1], aa[0]));
                     
                }else{
                   
                }
                  
            $( "#enddate" ).click(function() {
                    var frmDt=$("#startdate").val();
                    if(frmDt==""){
                        alert("Please select From Date");
                    }else{

                    }
                });
                
                $('#startdate').datepicker({dateFormat: 'dd-mm-yy', changeMonth: true, changeYear: true,minDate: '01-04-2015',
                    onSelect: function(dateText, inst) {
                        var selectedDate = dateText.split("-");
                        $("#enddate").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true, changeYear: true});
                        $("#enddate").datepicker("option", "minDate", new Date(selectedDate[2], --selectedDate[1], selectedDate[0]));
                        setTimeout(function(){
                            $( "#enddate" ).datepicker('show');
                        }, 16);     
                    }});
            
            $("#employee_search").autocomplete("employee_search_user.htm", {
                minChars: 1,
                matchContains: true
            });
            $('#employee_search').result(function(event, data, formatted) {
                if (data) {
                    $('#employee_id').val(data[1]);
                }
            });
            $("#manager").autocomplete("employee_search_user.htm", {
                minChars: 1,
                matchContains: true
            });
            $('#manager').result(function(event, data, formatted) {
                if (data) {
                    $('#manager').val(data[1]);
                }
            });
            $("#projectName").autocomplete("project_search.htm", {
                minChars: 1,
                matchContains: true
            });
            $('#project_search').result(function(event, data, formatted) {
                if (data) {
                    $('#projectName').val(data[1]);
                }
            });
       
        });
                
        function saveConsultant() {
                var firstname = document.getElementById("firstname").value;
                 var lastname = document.getElementById("lastname").value;
                 var file = document.getElementById("fileAttachment").value;
                 var company = document.getElementById("company").value;
                 var startdate = document.getElementById("startdate").value;
                 var enddate = document.getElementById("enddate").value;
                 var projectName = document.getElementById("projectName").value;
                 var address=document.getElementById("address").value;
                 var countryId=document.getElementById("countryId").value;
                  var regionId=document.getElementById("regionId").value;
                   var cityId=document.getElementById("cityId").value;
                   var contactname=document.getElementById("contactname").value;
                   var relationship=document.getElementById("relationship").value;
                   var mobile=document.getElementById("mobile").value;
                    var pmobile=document.getElementById("pmobile").value;
                   var aadhar=document.getElementById("aadhar").value;
                   var manager=document.getElementById("manager").value;
                    var practiceGroup=document.getElementById("practiceGroup").value;
                   var subPracticeGroup=document.getElementById("subPracticeGroup").value;
                   var startdate=document.getElementById("startdate").value;
                   var enddate=document.getElementById("enddate").value;
                  var mail=document.getElementById("mail").value;
                 var worklocation=document.getElementById("worklocation").value;
                  var atpos = mail.indexOf("@");
                    var dotpos = mail.lastIndexOf(".");
                 /// var phoneno = /^\d{10}$/; 
                  var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
                    if (firstname == null || firstname == "") {
                        alert('Please enter firstname name');
                        return false;
                    }
                    if (lastname == null || lastname == "") {
                        alert('Please enter lastname name');
                        return false;
                    }
                    if (file == null || file == "") {
                        alert('Please Upload Request Form');
                        return false;
                    }
                    else if (company == null || company == "") {
                        alert('Please enter company name');
                        return false;
                    }
                    else if (worklocation == null || worklocation == "") {
                        alert('Please select worklocation name');
                        return false;
                    }
                     else if (startdate == null || startdate == "") {
                        alert('Please enter start date');
                        return false;
                    }
                     else if (enddate == null || enddate == "") {
                        alert('Please enter end date');
                        return false;
                    }
                    else if (projectName == null || projectName == "" || projectName=="Search by Project Code/Project Name") {
                        alert('Please enter Project code');
                        return false;
                    }
                    else if (address == null || address == "") {
                        alert('Please enter Address');
                        return false;
                    }
                    else if (countryId == null || countryId == "") {
                        alert('Please Select Country');
                        return false;
                    }
                    else if (regionId == null || regionId == "") {
                        alert('Please Select Region');
                        return false;
                    }
                    else if (cityId == null || cityId == "") {
                        alert('Please Select City');
                        return false;
                    }
                    else if (contactname == null || contactname == "") {
                        alert('Please enter contactname');
                        return false;
                    }
                    else if (relationship == null || relationship == "") {
                        alert('Please enter relationship');
                        return false;
                    }
                    else if (mobile == null || mobile == "" || (/^\d{10}$/.test(mobile)==false)) {
                          
                        alert('Please enter Valid Mobile number');
                        return false;
                    }
                    else if (pmobile == null || pmobile == "" || (/^\d{10}$/.test(pmobile)==false)) {
                          
                        alert('Please enter Valid Mobile number');
                        return false;
                    }
                    else if (aadhar == null || aadhar == "" || aadhar.match(/[a-z]/i) || aadhar.match(/[^\w\s]/gi) || (aadhar.length!=12)) {
                        alert('Please Enter  Valid Aadhar number');
                        return false;
                    }
                    else if (manager == null || manager == "") {
                        alert('Please enter your Reporting manager name');
                        return false;
                    }
                    else if (practiceGroup == null || manager == "") {
                        alert('Please Select Practice group');
                        return false;
                    }
                    else if (subPracticeGroup == null || subPracticeGroup == "") {
                        alert('Please Select Sub Practice group');
                        return false;
                    }
                    else if (startdate == null || startdate == "") {
                        alert('Please Select Contract startdate');
                        return false;
                    }
                    else if (manager == null || manager == "") {
                        alert('Please enter your Reporting manager name');
                        return false;
                    }
                    else if (enddate == null || enddate == "") {
                        alert('Please Select contract end date');
                        return false;
                    }
                    else if (mail == null || mail == "" || atpos<1 || dotpos<atpos+2 || dotpos+2>=mail.length) {
                        alert('Please Enter Valid Mail id');
                        return false;
                    }
                   
                    else {
                        $('#addConsultantPage').attr("action", "saveConsultant.htm");
                        document.addConsultantPage.submit();
                        startLoading();
                        return true;
                    }
            
        }
             
                
    </script>
</head>
<body>      
    <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150;top: 275px; left: 525px;text-align:center"><img src="${pageContext.request.contextPath}/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <div id="container">
        <div class="container_inner">
            <div class="page_heading">
                Add Consultant
                <div  class="listLink">
                    <img src="/BulkUpload/images/icon_list.png">
                    <a href="pcList.htm" style="text-decoration:none;color: #4C83B3;" onclick="showFeedbacksList();">List Consultants</a>
                </div>
            </div>
        </div>
        <div class="notice_page">
            <div style="float:left;"><img alt="Information" style="width: 22px;margin-top: 4px;margin-left: 4px;" title="Information" src="${pageContext.request.contextPath}/images/icon_alert.png" /></div>
            <div class="instruction">
                <img alt="Information" title="Information" src="${pageContext.request.contextPath}/images/tick.png" />
                <label>Field marked in are <span style="color: red;" for="fine">*</span> mandatory</label>
            </div>    
            </div>
        </div>
        
        <%
            String modId = (String) request.getSession().getAttribute("modId");
        %>
        <input type="hidden" id="modId" name="modId" value="<%=modId%>" />
        <div id="commonformbg">
            <form name="addConsultantPage" method="post" id="addConsultantPage" enctype="multipart/form-data">
                <table id="addFormTable">
                    <tbody>
                        <tr>
                            <td colspan="4"><legend>Personal Details</legend></td>
                        </tr>
                        <tr>
                            <td><label for="name">Consultant Firstname<span style="color: red;" for="fine">*</span></label></td>
                            <td><input type="text" id="firstname" name="firstname"/> 
                            </td>
                             <td><label for="name">Consultant Lastname<span style="color: red;" for="fine">*</span></label></td>
                            <td><input type="text" id="lastname" name="lastname"/> 
                            </td>
                           
                        </tr>
                        <tr>
                             <td>
                                <label for="company">Partnering company name<span style="color: red;" for="fine">*</span></label>
                            </td>
                            <td>
                                <input type="text" id="company" name="company"  class ="textbox"  />
                            </td>
                            <td>
                                <label for="worklocation">Work Location<span style="color: red;" for="fine">*</span></label>
                            </td>
                            <td>
                                <select name="worklocation" id="worklocation" class="required selectbox_new ">
                                      <option value="">--Select--</option>
                                    <c:forEach items="${worklocationList}" var="wl" varStatus="i">
                                        <option value="${wl.workLocationId}" >${wl.workLocationName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4"><legend>Request Form Upload</legend></td>
                        </tr>
                        <tr>
                            <td>
                                <label for="file">File<span style="color: red;" for="fine">*</span></label>
                            </td>
                            <td>
                                <input type="file" value="" id="fileAttachment" name="file">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4"><legend>Contact Details</legend></td>
                        </tr>
                         <tr>
                            <td>
                                <label for="address">Personal Address Line<span style="color: red;" for="fine">*</span></label>
                            </td>
                            <td>
                                <textarea rows="1" cols="20" id="address" name="address" class="employeeAddress"></textarea>
                            </td>
                            
                        </tr>
                        <tr>
                            <td>
                                <label for="mail">HTL mail Address<span style="color: red;" for="fine">*</span></label>
                            </td>
                            <td>
                                <input type="text" id="mail" name="mail"  class ="textbox"  />
                            </td>
                            <td>
                                <label for="country">Country<span style="color: red;" for="fine">*</span></label>
                            </td>
                            <td>
                                 <select name="countryId" id="countryId" class="required selectbox_new ">
                                      <option value="">--Select--</option>
                                    <c:forEach items="${countryList}" var="country" varStatus="i">
                                        <option value="${country.id}" >${country.countryName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                           
                        </tr>
                        <tr>
                             <td>
                                <label for="region">Region<span style="color: red;" for="fine">*</span></label>
                            </td>
                            <td>
                                <select name="regionId" id="regionId" class="required selectbox_new ">
                                   <option value="">--Select--</option>
                                    <c:forEach items="${regionList}" var="region" varStatus="i">
                                        <option value="${region.id}">${region.regionName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <label for="city">City<span style="color: red;" for="fine">*</span></label>
                            </td>
                            <td>
                                <select name="cityId" id="cityId"  class="required selectbox_new ">
                                    <option value="">--Select--</option>
                                    <c:forEach items="${cityList}" var="cityName" varStatus="i">
                                        <option value="${cityName.id}">${cityName.cityName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>
                                <label for="mobile">Mobile Number<span style="color: red;" for="fine">*</span></label>
                            </td>
                            <td>
                                <input type="text" id="pmobile" name="pmobile"  class ="textbox"  />
                            </td>
                            <td>
                                <label for="aadhar">Aadhar Number<span style="color: red;" for="fine">*</span></label>
                            </td>
                            <td>
                                <input type="text" id="aadhar" name="aadhar"  class ="textbox" maxlength="12" />
                            </td>

                        </tr>
                        <tr></tr>
                        <tr>
                            <td colspan="4"><legend>Emergency Contact Details</legend></td>
                        </tr>
                        <tr>
                            <td>
                                <label for="contactname">Contact Person Name<span style="color: red;" for="fine">*</span></label>
                            </td>
                            <td>
                                <input type="text" id="contactname" name="contactname"  class ="textbox"  />
                            </td>
                            <td>
                                <label for="relationship">Relationship<span style="color: red;" for="fine">*</span></label>
                            </td>
                            <td>
                                <input type="text" id="relationship" name="relationship"  class ="textbox"  />
                            </td>

                        </tr>
                        <tr>
                            <td>
                                <label for="mobile">Contact Number<span style="color: red;" for="fine">*</span></label>
                            </td>
                            <td>
                                <input type="text" id="mobile" name="mobile"  class ="textbox"  />
                            </td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="4"><legend>Project Details</legend></td>
                        </tr>
                        <tr>
                            <td>
                                <label for="projcode">Project Code<span style="color: red;" for="fine">*</span></label>
                            </td>
                            <td>
                                <input type="text" id="projectName" name="projectName"  value="Search by Project Code/Project Name" onfocus="if (this.value == 'Search by Project Code/Project Name')
                                        this.value = '';" onblur="if (this.value == '')
                                        this.value = 'Search by Project Code/Project Name';" >
                            </td>
                            <td>
                                <label for="rmId">HTL Reporting Manager<span style="color: red;" for="fine">*</span></label>
                            </td>
                           <td>
                             <input type="text" id="manager" name="manager"  class ="textbox"  />  
                           </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="sbu">Practice Group<span style="color: red;" for="fine">*</span></label>
                            </td>
                            <td>
                                <select name="practiceGroup" id="practiceGroup">
                                    <option value="">--Select--</option>
                                    <c:forEach items="${pgList}" var="pgListValue" varStatus="i">
                                        <option value="${pgListValue.id}">${pgListValue.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <label for="subsbu">Sub Practice Group<span style="color: red;" for="fine">*</span></label>
                            </td>
                            <td>
                                <select name="subPracticeGroup" id="subPracticeGroup">
                                    <option value="">--Select--</option>
                                    <c:forEach items="${subPgList}" var="subPgListValue" varStatus="i">
                                        <option value="${subPgListValue.id}" >${subPgListValue.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="startdate">Contract Start Date<span style="color: red;" for="fine">*</span></label>
                            </td>
                            <td>
                                <input type="text" id="startdate" name="startdate" readonly/>
                            </td>
                            <td>
                                <label for="enddate">Contract End Date<span style="color: red;" for="fine">*</span></label>
                            </td>
                            <td>
                                <input type="text" id="enddate" name="enddate" readonly/>
                            </td>
                        </tr>
                        
                        <c:set var="count" value="20" scope="page" />
                        <c:set var="count" value="${count + 1}" scope="page"/>
                    </tbody>
                </table>
                <div id="footer" style="margin: 10px 0px">
                    <input class="submitbutton_TS" type="button" onclick="saveConsultant();"  value="Submit"/>
                    <input class="cancelbutton_TS"  type="reset" onclick="window.history.go(-1); return false;" value="Back" />
                </div>
            </form>
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
        </script>
    </div>
</body>
</html>
