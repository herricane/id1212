<%--
  Created by IntelliJ IDEA.
  User: herry
  Date: 11/21/20
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getSession().getAttribute("result") == null) {
        response.sendRedirect("index.jsp?error=3");
    }
    int result = (int) session.getAttribute("result");
    request.setAttribute("r", result);
%>
<html>
<head>
    <title>Online Quiz</title>
</head>
<body>
<h2>Result</h2>
<p>You got ${r} questions right!</p>
<a href="quiz.jsp">Take the quiz again</a>
<br><br>
<a href="index.jsp">Back to home</a>
</body>
</html>
