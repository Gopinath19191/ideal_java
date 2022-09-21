<%@include file="header.jsp" %>
    <head>
        <title>Login Page</title>
        <style type="text/css">
            input[type=text] {
                font-family: 'Arial';
                background: white
            }
            
    </style>
    </head>
  <body>

      <span class="topheading">OJF (Online Joining Formalities)</span>
      <div class="loginContent">
          <div class="innerContent">Authentication Required</div>
          <div class="imageLockContent"><img src="${pageContext.request.contextPath}/css/images/padlock.png" alt="" /></div>
          <div class="imageSeparatorContent"><img src="${pageContext.request.contextPath}/css/images/separator.png" alt="" /></div>
          <div class="loginInfo">
                <form name="login" method="POST" action="${pageContext.request.contextPath}/validate.htm" id="validateForm">
                    <div class="errmessage">
                        <c:if test="${invalid_login != null}">
                            ${invalid_login}
                        </c:if>
                    </div>
                    <label class="loginUserName">Username</label>
                    <c:if test="${param.empid == null}">
                        <td><input class="required qpd_usernamefield" name="userName" id="userName" type="text" maxlength="255" value="" style = "width:65%;"/></td>
                    </c:if>
                    <c:if test="${param.empid != null}">
                        <td>
                            <label>${param.empid}</label>
                            <input class="required qpd_usernamefield" name="userName" id="userName" type="hidden" maxlength="255" value="${param.empid}" />
                      </td>
                      </c:if>
                    <label  class="loginPassword">Password</label>
                    <input class="required qpd_passwordfield" name="userPassword" id="userPassword" type="password"  value="" />
                    <input name="loginSubmit" type="submit" value="" id="qpd_loginbutton">
              </form>
          </div>
    </div>
    <div class="footerContent">
        Note : If you have any problem in logging in, contact idealsupport@hindujatech.com
    </div>


      
    </body>
</html>
<script type="text/javascript">
        $(document).ready(function() {
                    $("#validateForm").validate({
                        errorElement:"div",
                        errorClass:"customError"
                    });
                });
</script>