<%--
  Created by IntelliJ IDEA.
  User: zhengjiayu
  Date: 2023/1/2
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin</title>
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
        font-size: 40px;
    }
    .item{
        border: 3px solid #ccc;
        margin-top: 3px;

    }
</style>
<body>
<div>
    <ul class="nav">
        <li><a href="${pageContext.request.contextPath}/admin">Admin </a></li>
        <li><a href="${pageContext.request.contextPath}/logout">Logout </a></li>
        <li><a href="${pageContext.request.contextPath}/admin/feedback">Feedback </a></li>
        <li><a href="${pageContext.request.contextPath}/admin/quiz">Quiz </a></li>
        <li><a href="${pageContext.request.contextPath}/admin/userProfile">User Profile</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/question">Question Edit</a></li>
    </ul>

    <div id="content">
        <h1>Quiz Result</h1>
        <form action="/admin/quiz/result" method="post">
            <div class="feedback-box">
                <label style="color: #cccccc; font-size:20px">This is the Quiz Result Record</label>

                <div class="result-item">
                    <c:forEach items="${quiz.questions}" var="question" varStatus="loop">
                        <div class="item">
                            <div>
                                <p style="color: #cccccc">
                                        Question: ${question.description}
                                </p>
                                <p style="color:#cccccc;">
                                    <c:forEach items="${question.choices}" var="choice" varStatus="loop">
                                        Choice: ${choice.description} |
                                    </c:forEach>
                                </p>
                                <p style="color: #cccccc">
                                    User selection: ${question.userChoice},
                                    Correct answer: ${question.correctChoice}
                                </p>

                            </div>
                        </div>
                    </c:forEach>
                </div>

            </div>
        </form>

    </div>
</div>

</body>
</html>
