<%@ page import="java.util.List" %>
<%@ page import="se.kth.bean.QuestionsEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: herry
  Date: 11/18/20
  Time: 10:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getSession().getAttribute("user") == null) {
        response.sendRedirect("index.jsp?error=3");
    }
    List<QuestionsEntity> questions = (List<QuestionsEntity>) session.getAttribute("questions");
    request.setAttribute("qs", questions);
%>
<html>
<head>
    <title>Online Quiz</title>
</head>
<body>
<h2>Take the Quiz!</h2>
<form method="post" action="${pageContext.request.contextPath}/result">
    <c:forEach items="${qs}" var="q" varStatus="counter">
        <div>${counter.count}. ${q.question}</div>
        <input type="radio" name="${q.id}" value="1" id="${q.id}A">
        <label for="${q.id}A">A. ${q.choice1}</label>
        <br>
        <input type="radio" name="${q.id}" value="2" id="${q.id}B">
        <label for="${q.id}B">B. ${q.choice2}</label>
        <br>
        <input type="radio" name="${q.id}" value="3" id="${q.id}C">
        <label for="${q.id}C">C. ${q.choice3}</label>
        <br>
        <input type="radio" name="${q.id}" value="4" id="${q.id}D">
        <label for="${q.id}D">D. ${q.choice4}</label>
        <br><br>
    </c:forEach>
    <input type="submit" value="Submit!">
</form>
</body>
</html>
