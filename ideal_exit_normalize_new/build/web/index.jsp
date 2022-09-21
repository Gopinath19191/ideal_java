<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<html>
    <head>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#loginForm").validate();
            });
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <body>
         <span class="topheading">EPM (Exit Process Management)</span>
      <div class="loginContent">
          <div class="innerContent">Authentication Required</div>
          <div class="imageLockContent"><img src="/ideal_qpd/css/images/padlock.png" alt="" /></div>
          <div class="imageSeparatorContent"><img src="/ideal_qpd/css/images/separator.png" alt="" /></div>
          <div class="loginInfo">
              <form method="post" action="${pageContext.request.contextPath}/loginAuthenticate.htm" id="loginForm" name="loginForm">
                  <div id="errormessage" class="error-message">${Result}
                      <c:if test="${errorMsg ne null}">${errorMsg}</c:if>
                  </div>
                  <label class="loginUserName">Username</label>
                  <input class="required qpd_usernamefield" name="userName" id="userName" type="text" maxlength="255" value="" />
                  <label  class="loginPassword">Password</label>
                  <input class="required qpd_passwordfield" name="password" id="password" type="password"  value="" />
                  <input name="loginSubmit" type="submit" value="" id="qpd_loginbutton" class="">
              </form>
          </div>
      </div>
    </body>
    <%--<body class="ext-gecko ext-gecko3">
        <div class="wrap">
            <div class="topBand">EPM (Exit Process Management)</div>
            <div class="loginImage"></div>
            <div class="loginsplitarea">
                <form method="post" action="${pageContext.request.contextPath}/loginAuthenticate.htm" id="loginForm" name="loginForm">
                    <table>
                        <tbody>
                            <tr>
                                <td align="right" colspan="2" width="100%" height="25">
                                    <div id="errormessage" class="error-message" style="width:200px;margin-left:173px;">${Result}
                                        <c:if test="${errorMsg ne null}">${errorMsg}</c:if></div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div id="loginDiv">
                        <label class="username" style="float:left;width:30%;margin-left:17.5%">Username :</label>
                        <input class="usernamefield required number"  style="float:left;width:35%;" name="userName" id="userName" type="text" maxlength="10" value="" >
                        <div style="clear:both"></div>
                        <label class="password" style="float:left;width:30%;margin-left:17.5%">Password :</label>
                        <input class="passwordfield required" style="float:left;width:35%;" name="password" id="password" type="password" maxlength="30" value="" />
                        <div style="clear:both"></div>
                        <input type="submit" name="btnLogin" id="btnLogin" value="Login" class="buttons submitbutton qpd_loginbutton" >
                        <input type="reset" name="btnCancel" id="btnCancel" value="Cancel" class="buttons resetbutton qpd_cancelbutton" >
                        <div style="clear:both"></div>
                    </div>

                </form>
            </div>
        </div>
    </body>--%>
</head>
</html>