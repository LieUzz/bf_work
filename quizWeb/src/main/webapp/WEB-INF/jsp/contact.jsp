<%--
  Created by IntelliJ IDEA.
  User: zhengjiayu
  Date: 2023/1/1
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>


</head>
<body>
<form action="/contact" method="post">

    <ul class="nav">
        <li><a href="${pageContext.request.contextPath}/home">Home </a></li>
        <li><a href="${pageContext.request.contextPath}/logout">Logout </a></li>
        <li><a href="${pageContext.request.contextPath}/register">Registration </a></li>
        <li><a href="${pageContext.request.contextPath}/feedback">Feedback </a></li>
        <li><a href="${pageContext.request.contextPath}/contact">Contact Us </a></li>

    </ul>
    <div class="quiz-box">
            <textarea rows="10" cols="30" name="feedback">
            </textarea>
    </div>

    <div class="submit-box">
        <button id= "submit-button" class="btn btn-primary">submit</button>
    </div>
</form>
</body>
</html>
