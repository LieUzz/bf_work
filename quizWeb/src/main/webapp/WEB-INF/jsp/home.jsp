<%--
  Created by IntelliJ IDEA.
  User: zhengjiayu
  Date: 2022/12/30
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Online Quiz！</title>
    <script type="text/javascript" src="../static/jsp/lx.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../static/css/lx.css">

</head>
<style>
    body,input{
        margin: 0;
        padding: 0;
        background: #4A374A;
    }
    *{
        margin:0;
        padding:0;
        font-size:14px;
    }
    a {
        color: #cccccc;
        text-decoration:none
    }
    .nav li {
        float:left;
    }
    .nav li a {
        display:block;
        text-align:center;
        height:50px;
        line-height:30px;
        width:350px;
        background-color: #333333;
        margin-bottom:1px;}
    .nav li a:hover {
        background-color:#F60;
        color:#fff
    }

    a:link,a:visited {
        font-weight:bold;
        color:#FFFFFF;
        background-color:#cccccc;
        text-align:center;
        padding:6px;
        text-decoration:none;
        text-transform:uppercase;
    }
    a:hover,a:active {
        background-color:#cccccc;
    }
    #content{
        position: absolute;
        top: 30%;
        left:35%;
        margin: -150px 0 0 -150px;
        width: 1100px;
        height: 1000px;
    }
    h1 {
        color: #fff;
        text-shadow:0 0 10px;
        letter-spacing: 1px;
        text-align: center;
    }
</style>


<body>
<div>
    <ul class="nav">
        <li><a href="${pageContext.request.contextPath}/home">Home </a></li>
        <li><a href="${pageContext.request.contextPath}/logout">Logout </a></li>
        <li><a href="${pageContext.request.contextPath}/register">Registration </a></li>
        <li><a href="${pageContext.request.contextPath}/feedback">Feedback </a></li>
        <li><a href="${pageContext.request.contextPath}/contact">Contact Us </a></li>

    </ul>
    <div id="content">
        <h1>QUIZ</h1>
        <form action="/home" method="post">
            <div class="quiz-box">
                <span class="require">*</span>
                <label style="color: #cccccc; font-size:20px">There are several quiz, choose one to take a quiz. </label>
                <div class="quiz-input">
                    <c:forEach items="${categories}" var="category" varStatus="loop">
                        <div>
                            <input type="radio"
                                   name="categoryId"
                                   value="${category.id}" style="display: inline-block; color: #cccccc"/>
                            <p style="color: #cccccc">${category.category_name}</p>
                        </div>
                    </c:forEach>
                </div>

            </div>
            <div class="submit-box">
                <%--            <button id="submit-button" name="submit" class="btn btn-primary">submit</button>--%>
                <input type="submit" class="btn btn-info" name="submit" value="submit">
            </div>

            <div class="user-quiz">
                <c:forEach items="${quizList}" var="quiz" varStatus="loop">
                    <label for="userquiz" style="color: #cccccc; display: inline-block">${quiz.quiz_name} quiz: ${quiz.start_time}</label>
                    <div>
                        <input type="submit"
                               id="userquiz"
                               name="submit"
                               class="btn btn-info"
                               value="${quiz.id}"/>
                    </div>
                </c:forEach>
            </div>


        </form>
    </div>

</div>
</body>
</html>
