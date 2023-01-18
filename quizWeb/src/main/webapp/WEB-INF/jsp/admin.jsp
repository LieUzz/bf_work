<%--
  Created by IntelliJ IDEA.
  User: zhengjiayu
  Date: 2023/1/2
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
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
    #title{
        position: absolute;
        top: 50%;
        left:48%;
        margin: -150px 0 0 -150px;
        width: 300px;
        height: 300px;
    }
    h1 {
        color: #fff;
        text-shadow:0 0 10px;
        letter-spacing: 1px;
        text-align: center;
        font-size: 80px;
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

</div>
<div id="title">
    <h1>ADMIN</h1>
</div>

</body>
</html>
