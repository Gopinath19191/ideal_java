<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="header.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>
<script>
    function goToValidate(){
        var userToken = "9139daac78d809086f59cbd057e6a389";
        var curDate = new Date();
        alert(curDate);
        var dttmTime = curDate;
        $.ajax({
            url : "http://10.18.1.45:8080/LoginAuthentication/webresources/loginController/loginAuthenticate?username=16047&password=admin",
            type : "GET",
            //dataType : "JSON",
            success : function(data){
                alert(data.IsSuccess); 
        if(data.IsSuccess == true){       
            alert("Success");
//        $("#successMsg").html(data.success);
        }else{
//              window.location.href = "success.htm?success="+data.success;
        }
            }
        });
    }
</script>
    <body>
        <h1>User Authentication</h1>
        <form action="#" method="POST" id="authenticate" name="authenticate" >
            <table>
                <tr>
                    <td>
                        <label for="username">User Name</label>
                        <input type="text" name="username" id="username" value=""/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="password">Password</label>
                        <input type="text" name="password" id="password" value=""/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="button" name="submit" id="submit" value="Login" onclick="goToValidate();"/>
                    </td>
                </tr>
                <tr>
                    <td><div id="successMsg" style="color: green"></div></td>
                </tr>
            </table>
        </form>
    </body>
</html>
