<%--
  Created by IntelliJ IDEA.
  User: zhengjiayu
  Date: 2023/1/1
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<style>
    body,input{
        margin: 0;
        padding: 0;
        /*background: #4A374A;*/
    }
    ul
    {
        list-style-type:none;
        margin:0;
        padding:0;
        padding-top:6px;
        padding-bottom:6px;
    }
    li
    {
        display:inline;
    }
    a:link,a:visited
    {
        font-weight:bold;
        color:#FFFFFF;
        background-color:#98bf21;
        text-align:center;
        padding:6px;
        text-decoration:none;
        text-transform:uppercase;
    }
    a:hover,a:active
    {
        background-color:#7A991A;
    }

</style>
<body>

<ul class="nav">
    <li><a href="${pageContext.request.contextPath}/home">Home </a></li>
    <li><a href="${pageContext.request.contextPath}/logout">Logout </a></li>
    <li><a href="${pageContext.request.contextPath}/register">Registration </a></li>
    <li><a href="${pageContext.request.contextPath}/feedback">Feedback </a></li>
    <li><a href="${pageContext.request.contextPath}/contact">Contact Us </a></li>

</ul>
<h1> Please choose your rating and enter your feedback</h1>

<form action="/feedback" method="post" onSubmit="return fnFeedback()">
    <div class="quiz-input">
        <input type="radio" id="star1" name="star" value="1" checked="checked" />Star1
        <input type="radio" id="star2" name="star" value="2" />Star2
        <input type="radio" id="star3" name="star" value="3" />Star3
        <input type="radio" id="star4" name="star" value="4" />Star4
        <input type="radio" id="star5" name="star" value="5" />Star5
    </div>


    <div class="quiz-box">
            <textarea rows="10" cols="30" name="feedback" >
            </textarea>
    </div>

    <div class="submit-box">
        <button id= "submit-button" class="btn btn-primary" onclick="fnFeedback()">submit</button>
    </div>

</form>
</body>
</html>
