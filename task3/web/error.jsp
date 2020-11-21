<%--
  Created by IntelliJ IDEA.
  User: herry
  Date: 11/18/20
  Time: 5:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" language="java" %>
<html>
<head>
    <title>Online Quiz</title>
</head>
<body>
<h2>Something went wrong...</h2>
<%
    String message = exception.getMessage();
    out.print(message);
%>
</body>
</html>
