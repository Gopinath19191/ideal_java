<% response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%--<%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>--%>
    <%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    
        <meta http-equiv="X-UA-Compatible" content="IE=8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control"  content="no-cache"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="0"/>
        <link rel="stylesheet" href="css/cake.generic.css" type="text/css"/>
        <link rel="stylesheet" href="css/ui.all.css" type="text/css"/>
        <link rel="stylesheet" href="css/jquery-ui-1.8.5.custom.css" type="text/css"/>
        <link rel="stylesheet" href="css/autoSuggest.css" type="text/css"/>
                
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="js/jquery.form.2.49.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.5.custom.min.js"></script>
        
        <script type="text/javascript" src="js/jquery.validate.1.7.mod.js"></script>
        <script type="text/javascript" src="js/additional-methods.js"></script>
        <script type="text/javascript" src="js/number_validate.js"></script>
        
        <script type="text/javascript">
            $(document).ready(function(){
                $(".datepicker").datepicker({
                    changeYear: true,
                    changeMonth: true,
                    dateFormat:'dd-mm-yy',
                    showButtonPanel: false
                });
            });
        </script>
    </head>
<noscript>
    <table>
        <tr>
        <td>
            
        <h1>Error! - Javascript Disabled</h1>
        <font face="Arial, Helevetica" color="red" >You must have JavaScript enabled on your browser.</font>
        </td>
        </tr>
        </table>
</noscript>
