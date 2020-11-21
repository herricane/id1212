<%--
  Created by IntelliJ IDEA.
  User: herry
  Date: 11/18/20
  Time: 6:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Online Quiz</title>
</head>
<body>
<h2>Register</h2>
<form action="${pageContext.request.contextPath}/register" method="post">
    <label for="email">Email</label>
    <input type="text" id="email" name="email">
    <br>
    <label for="username">Username</label>
    <input type="text" id="username" name="username">
    <br>
    <label for="password">Password</label>
    <input type="password" id="password" name="password">
    <br><br>
    <input type="submit" value="Register" onclick="register_alert()">
</form>
</body>

<script>
    function register_alert() {
        window.alert("Successfully registered! Now heading you back home.")
    }
</script>
</html>
