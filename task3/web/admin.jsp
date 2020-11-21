<%--
  Created by IntelliJ IDEA.
  User: herry
  Date: 11/18/20
  Time: 9:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getSession().getAttribute("admin") == null) {
        response.sendRedirect("index.jsp?error=2");
    }
%>
<html>
<head>
    <title>Online Quiz Admin</title>
    <style>
        .input-box {
            width: 300px;
            height: 30px
        }
    </style>
</head>
<body>
<h2>Admin Interface</h2>
<p>Add a question:</p>
<form action="${pageContext.request.contextPath}/add-question" id="q-form" method="post">
    <label for="question">Question:</label>
    <br>
    <input type="text" id="question" name="question" class="input-box">
    <br><br>
    <label for="choice-1">Choice 1:</label>
    <br>
    <input type="text" id="choice-1" name="choice-1" class="input-box">
    <br><br>
    <label for="choice-2">Choice 2:</label>
    <br>
    <input type="text" id="choice-2" name="choice-2" class="input-box">
    <br><br>
    <label for="choice-3">Choice 3:</label>
    <br>
    <input type="text" id="choice-3" name="choice-3" class="input-box">
    <br><br>
    <label for="choice-4">Choice 4:</label>
    <br>
    <input type="text" id="choice-4" name="choice-4" class="input-box">
    <br><br>
    <label for="answer">Answer:</label>
    <select id="answer" name="answer">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
    </select>
    <br><br>
    <input type="submit" value="Add" onclick="add_alert()">
</form>
<br>
<a href="index.jsp">Back to home</a>

<script>
    function add_alert() {
        window.alert("New question added.");
    }
</script>
</body>
</html>
