<% response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <%-- <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        </body>--%>
    <head>
       <%-- <script type="text/javascript">
     var loadingDivObj=(document.all);
     var ns4=document.layers;
     var ns6=document.getElementById&&!document.all;
     var ie4=document.all;
function startLoading(){
     if (ns4){
     	loadingDivObj=document.loadingDiv;
     }else if (ns6){
     	loadingDivObj=document.getElementById("loadingDiv").style;
     }else if (ie4){
     	loadingDivObj=document.all.loadingDiv.style;
     	}
}
startLoading();
     function stopLoading(){
     if(ns4){loadingDivObj.visibility="hidden";}
     else if (ns6||ie4) loadingDivObj.display="none";
     }
  /*
$(window).load(function() {
  stopLoading();
});

$(body).unload(function() {
  startLoading();
});
*/
window.unload=function(){startLoading();}
window.onload =function(){stopLoading();}

</script>
<style type="text/css">
#loadingDiv img{ border: none; }
#loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1}
/** For IE6 enable this

  * html #loadingDiv{
	background-color: transparent;
	background-image: url(blank.gif);
	filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src="overlay.png", sizingMethod="scale");
}*/
</style>--%>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control"  content="no-cache"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="0"/>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/icon.ico" />
        <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.5.js"></script>--%>
        <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.5.1.min.js"></script>--%>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
        <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>--%>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.17.custom.min.js"></script>
        <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.16.custom.min.js"></script>--%>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/date.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/js.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.slider.js"></script>

        <link href="${pageContext.request.contextPath}/css/qpd.css" rel="stylesheet" type="text/css" />
        <%--<link href="${pageContext.request.contextPath}/css/flick/jquery-ui-1.8.12.custom.css" rel="stylesheet" type="text/css"/>--%>
        <link href="${pageContext.request.contextPath}/css/flick/jquery-ui-1.8.17.custom.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/jquery.ui.all.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/demos.css" rel="stylesheet" type="text/css"/>

        <title>Exit Management Process</title>
    </head>
    

