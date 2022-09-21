<%--
    Document   : login
    Created on : Jul 5, 2010, 2:59:09 PM
    Author     : HARIHARAN.C
--%>

<%@include file="header.jsp" %>
    <head>
        <title>Login Page</title>
        <style type="text/css">
#masterDiv{
    margin:auto;
    width:500px;
}

form div{
    clear:none;
}
#loginTable{
    width:300px;
    border:1px solid #000099;
    
}
label{
    font-size: 13px;
    font-weight: bold;
}
input{
    width:150px;
}
span.customError{
    float: left;
    margin-left:181px;
    width: 335px;
    font-family: Calibiri, Geneva, sans-serif;
    font-size: 11px;
}
    </style>
    </head>
  <%--<c:if test="${!empty(loginId)}">
      <c:redirect url="/com/defiance/ideal/resumemgmt/begin.do"></c:redirect>
  </c:if>--%>
  <%--${empid}--%>
  <%--<c:if test="${!empty(loginId)}">
      <c:redirect url="/com/defiance/ideal/resumemgmt/begin.do"></c:redirect>
  </c:if>--%>
  <body>
      <div class="wrap">
        <%--<div class="topBand">QPD (Quaterly Performance Dialogue) </div>--%>
        <div class="topBand">Annual Appraisal </div>
        <div class="loginImage"></div>
        <div class="loginsplitarea">
        <form name="login" method="POST" action="validate.do" id="validateForm">
            <div id="masterDiv">
                    <label class="username">Username :</label>
                    <c:if test="${param.empid == null}">
                            <input class="required qpd_usernamefield" name="userName" id="userName" type="text" maxlength="255" value="" />
                    </c:if>
                      <c:if test="${param.empid != null}">
                          <label>${param.empid}</label>
                                <input class="required" name="userName" id="userName" type="hidden" maxlength="255" value="${param.empid}" />
                      </c:if>
                    <label class="password">Password :</label>
                    <input class="required qpd_passwordfield" name="userPassword" id="userPassword" type="password"  value="" />
                    <div class="error-message">
                        <c:if test="${invalid_login != null}">
                        ${invalid_login}
                        </c:if>
                    </div>
                    <input name="loginSubmit" type="submit" value="" id="qpd_loginbutton" class="buttons submitbutton qpd_loginbutton">
                <input name="reset" type="reset" value="" id="qpd_cancelbutton" class="buttons resetbutton qpd_cancelbutton">
            </div>
        </form>
         </div>
      </div>
    </body>
</html>
<script type="text/javascript">
        $(document).ready(function() {
                    $("#validateForm").validate({
                        errorElement:"span",
                        errorClass:"customError"
                    });
                });
</script>