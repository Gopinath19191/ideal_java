<%-- 
    Document   : furtherAdvance
    Created on : Dec 13, 2012, 11:06:16 AM
    Author     : 14583
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/header.jsp" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="cn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function openAdvanceForm(){
                var windowWidth;
                var windowHeight;
                $("#advanceDiv").click(function(){
                    //alert("test data");
                    windowWidth = $(window).width();
                    windowHeight = $(window).height();
                    var lightBoxContentWidth = $(".lightbox-content").width();
                    var lightBoxContentHeight = $(".lightbox-content").height();
                    var LeftPos = (windowWidth - lightBoxContentWidth) / 2;
                    var TopPos = (windowHeight - lightBoxContentHeight) / 2;
                    $("body").append("<div class='lightbox-overlay'></div>");
                    $(".lightbox-overlay").css({'width':windowWidth, 'height':windowHeight});
                    $(".lightbox-content").css({'top':TopPos, 'left':LeftPos}).fadeIn();
               });
            }
        </script>
        <style>
            .lightbox-overlay{
                position: absolute;
                width: 100%;
                height: 100%;
                top: 0;
                left: 0;
                background: #fff;
                opacity:0.8;   
                filter: alpha(opacity:80);
                ZOOM: 1px;
                z-index: 1;
            }
            .lightbox-content{
                width: 500px;
                height:400px;
                background: #12aaed;
                display: none;
                position: absolute;
                z-index: 2;
            }
            </style>
    </head>
    <body>
        <div id="advanceDiv">
            <form name="advanceForm" id="advanceForm" action="" method="POST" >
                <input type="button" name="advance" id="advance" value="Advance" onclick="openAdvanceForm();"  />
            </form>
        </div>
        <div class="lightbox-content">
            
        </div>
    </body>
</html>
