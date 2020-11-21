<%--
  Created by IntelliJ IDEA.
  User: herry
  Date: 11/18/20
  Time: 3:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Online Quiz</title>
</head>
<body>
  <h2>Welcome to the online quiz!</h2>
  <p>If you are new here: <a href="${pageContext.request.contextPath}/register.jsp">Register</a></p>
  <p>If you have registered before: <a href="${pageContext.request.contextPath}/login.jsp">Login</a></p>

  <script>
    let errorInfo = '<%= request.getParameter("error") %>';
    if (errorInfo === '1') {
      alert("Wrong username or password!")
    } else if (errorInfo === '2') {
      alert("You are not an admin!")
    } else if (errorInfo === '3') {
      alert("Please login first!")
    }
  </script>
</body>
</html>
