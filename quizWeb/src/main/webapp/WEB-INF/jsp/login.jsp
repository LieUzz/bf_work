<%--
  Created by IntelliJ IDEA.
  User: zhengjiayu
  Date: 2022/12/29
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Online Quiz！</title>
    <script type="text/javascript" src="../static/jsp/lx.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../static/css/lx.css">
</head>
<style>
    *{
        margin: 0;
        padding: 0;
        font-size: 14px;
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
        /*background-color:#cccccc;*/
        text-align:center;
        padding:6px;
        text-decoration:none;
        text-transform:uppercase;
    }
    a:hover,a:active {
        /*background-color:#cccccc;*/
    }


    html{
        width: 100%;
        height: 100%;
        overflow: hidden;
        /*font-style: sans-serif;*/
    }
    body{
        width: 100%;
        height: 100%;
        /*font-family: 'Open Sans',sans-serif;*/
        margin: 0;
        background-color: #4A374A;
    }
    #error_box{
        font-size: 20px;
        width: 350px;
    }
    #login{
        position: absolute;
        top: 30%;
        left:48%;
        margin: -150px 0 0 -150px;
        width: 300px;
        height: 300px;
    }
    #login h1{
        color: #fff;
        text-shadow:0 0 10px;
        letter-spacing: 1px;
        text-align: center;
    }
    h1{
        font-size: 2em;
        margin: 0.67em 0;
    }
    .input_box{
        width: 350px;
        height: 60px;
        margin-bottom: 10px;
        outline: none;
        padding: 10px;
        font-size: 20px;
        color: #fff;
        /*text-shadow:1px 1px 1px;*/
        border-top: 1px solid #312E3D;
        border-left: 1px solid #312E3D;
        border-right: 1px solid #312E3D;
        border-bottom: 1px solid #56536A;
        border-radius: 4px;
        background-color: #2D2D3F;
    }
    #register {
        /*display: inline-block; */
        margin-left: 33px;
        width: 150px
    }
</style>
<body>

<ul class="nav">
    <li><a href="${pageContext.request.contextPath}/home">Home </a></li>
    <li><a href="${pageContext.request.contextPath}/login">Login </a></li>
    <li><a href="${pageContext.request.contextPath}/register">Registration </a></li>
    <li><a href="${pageContext.request.contextPath}/feedback">Feedback </a></li>
    <li><a href="${pageContext.request.contextPath}/contact">Contact Us </a></li>
</ul>

<div>
    <div id="login">
        <h1>Login</h1>
        <form action="/login" method="post">
            <div >
                <input class="input_box" id="uname" type="text" name="username" placeholder="Please enter your Username" width="100">
            </div>
            <div >
                <input class="input_box" id="upass" type="password" name="password" placeholder="Please enter your Password">
            </div>
<%--            <div ><br></div>--%>
            <div style="width: 350px">
                <button type="submit" class="btn btn-primary" style="width: 150px">login</button>
                <a href="/register" >
                    <input type="button" id = "register" class="btn btn-info" name="register" value="register">
                </a>
            </div>
        </form>

        <%
            String errorInfo = (String) request.getAttribute("loginError");
            if (errorInfo != null) {
                %>
        <script type="text/javascript">
            alert("<%=errorInfo%>");
        </script>
        <%
            }
        %>

    </div>
</div>
</body>

</html>

