<%--
  Created by IntelliJ IDEA.
  User: zhengjiayu
  Date: 2022/12/30
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../static/css/lx.css">

</head>
<style >
    body,input{
        margin: 0;
        padding: 0;
        background: #4A374A;
    }
    input{
        display: inline-block;
        background: #fff;
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

    .container{
        width: 100%;
    }
    .register-box{
        position: relative;
        height: 1000px;
        width: 800px;
        top: 50px;
        margin: 0 auto;
        z-index: 99999;
        background:#ffffff;
        border: 7px solid #ccc;
    }
    .title-box{
        position: absolute;
        width: 300px;
        height: 50px;
        margin-left: 250px;
        margin-top: 5px;
        text-align: center;
        font-size: 28px;
        font-weight: 800;
        color: #ff5000;
        line-height: 50px;
    }
    .username-box{
        position: absolute;
        width: 600px;
        height: 40px;
        line-height: 40px;
        margin-top:100px;
        margin-left:80px;
        font-weight: 700;
    }
    .username-input{
        display: inline-block;
        margin-left: 58px;
        /*background: green;*/
    }
    #username{
        height: 35px;
        width: 290px;
        border: 2px solid #ccc;
        border-radius: 5px;
    }
    .userPassword-box{
        position: absolute;
        width: 600px;
        height: 40px;
        line-height: 40px;
        margin-top:180px;
        margin-left:80px;
        font-weight: 700;
    }
    .userPassword-input{
        display: inline-block;
        margin-left: 61px;
    }
    #userPassword{
        height: 35px;
        width: 290px;
        border: 2px solid #ccc;
        border-radius: 5px;
    }
    .userRePassword-box{
        position: absolute;
        width: 600px;
        height: 40px;
        line-height: 40px;
        margin-top:260px;
        margin-left:80px;
        font-weight: 700;
    }
    .userRePassword-input{
        display: inline-block;
        margin-left: 75px;
    }
    #userRePassword{
        height: 35px;
        width: 290px;
        border: 2px solid #ccc;
        border-radius: 5px;
    }
    .firstname-box{
        position: absolute;
        width: 600px;
        height: 40px;
        line-height: 40px;
        margin-top:340px;
        margin-left:80px;
        font-weight: 700;
    }
    .firstname-input{
        display: inline-block;
        margin-left: 61px;
    }
    #firstname{
        height: 35px;
        width: 290px;
        border: 2px solid #ccc;
        border-radius: 5px;
    }
    .lastname-box{
        position: absolute;
        width: 600px;
        height: 40px;
        line-height: 40px;
        margin-top:420px;
        margin-left:80px;
        font-weight: 700;
    }
    .lastname-input{
        display: inline-block;
        margin-left: 61px;
    }
    #lastname{
        height: 35px;
        width: 290px;
        border: 2px solid #ccc;
        border-radius: 5px;
    }

    .address-box{
        position: absolute;
        width: 600px;
        height: 40px;
        line-height: 40px;
        margin-top:500px;
        margin-left:80px;
        font-weight: 700;
    }
    .address-input{
        display: inline-block;
        margin-left: 75px;
    }
    #address{
        height: 35px;
        width: 290px;
        border: 2px solid #ccc;
        border-radius: 5px;
    }

    .userPhone-box{
        position: absolute;
        width: 600px;
        height: 40px;
        line-height: 40px;
        margin-top:580px;
        margin-left:80px;
        font-weight: 700;
    }
    .userPhone-input{
        display: inline-block;
        margin-left: 88px;
    }
    #userPhone {
        height: 35px;
        width: 290px;
        border: 2px solid #ccc;
        border-radius: 5px;
    }

    .userEmail-box{
        position: absolute;
        width: 600px;
        height: 40px;
        line-height: 40px;
        margin-top:660px;
        margin-left:80px;
        font-weight: 700;
    }
    .userEmail-input{
        display: inline-block;
        margin-left: 92px;
    }
    #userEmail{
        height: 35px;
        width: 290px;
        border: 2px solid #ccc;
        border-radius: 5px;
    }

    .isAdmin-box{
        position: absolute;
        width: 600px;
        height: 40px;
        line-height: 40px;
        margin-top:740px;
        margin-left:82px;
        font-weight: 700;
    }
    .isAdmin-input{
        display: inline-block;
        margin-left: 62px;
    }
    .require{
        color: red;
    }
    .submit-box{
        position:absolute;
        width: 80px;
        height: 40px;
        line-height: 40px;
        margin-top: 820px;
        margin-left:200px;
        border-radius: 5px;
        /*background: grey;*/
    }
    #submit-button{
        display: inline-block;
        width: 80px;
        height: 40px;
        border-radius: 5px;
        /*background: red;*/
    }
    .goLogin-box{
        position:absolute;
        width: 300px;
        height: 20px;
        margin-top: 820px;
        margin-left:320px;

    }
    #error_box{
        position:absolute;
        width: 600px;
        height: 40px;
        line-height: 40px;
        margin-top: 700px;
        margin-left:100px;
        border-radius: 5px;
        background: pink;
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

<div class="container">
    <div class="register-box">
        <div class="title-box">
            <span>REGISTER</span>
        </div>
        <form action="/register" method="post" onSubmit="return fnRegister()">
            <div class="username-box">
                <span class="require">*</span>
                <label for="username">Username</label>
                <div class="username-input">
                    <input type="text" id="username" name="username" placeholder="  Please Enter Your Username" />
                </div>
            </div>

            <div class="userPassword-box">
                <span class="require">*</span>
                <label for="userPassword">Password</label>
                <div class="userPassword-input">
                    <input type="password" id="userPassword" name="password" placeholder="  Please Enter your Password" />
                </div>
            </div>

            <div class="userRePassword-box">
                <span class="require">*</span>
                <label for="userRePassword">Confirm</label>
                <div class="userRePassword-input">
                    <input type="password" id="userRePassword" name="userRePassword" placeholder="  Please Confirm Your Password" />
                </div>
            </div>

            <div class="firstname-box">
                <span class="require">*</span>
                <label for="firstname">FirstName</label>
                <div class="firstname-input">
                    <input type="text" id="firstname" name="firstname" placeholder="  Please Enter Your Firstname" />
                </div>
            </div>

            <div class="lastname-box">
                <span class="require">*</span>
                <label for="lastname">LastName</label>
                <div class="lastname-input">
                    <input type="text" id="lastname" name="lastname" placeholder="  Please Enter Your Lastname" />
                </div>
            </div>

            <div class="address-box">
                <span class="require">*</span>
                <label for="address">Address</label>
                <div class="address-input">
                    <input type="text" id="address" name="address" placeholder="  Please Enter Your Address" />
                </div>
            </div>

            <div class="userPhone-box">
                <span class="require">*</span>
                <label for="userPhone">Phone</label>
                <div class="userPhone-input">
                    <input type="text" id="userPhone" name="phone" placeholder="  Please Enter Your Phone Number" />
                </div>
            </div>

            <div class="userEmail-box">
                <span class="require">*</span>
                <label for="userEmail">Email</label>
                <div class="userEmail-input">
                    <input type="text" id="userEmail" name="email" placeholder="  Please Enter Your Email" />
                </div>
            </div>

            <div class="isAdmin-box">
                <span class="require">*</span>
                <label >Is Admin</label>
                <div class="isAdmin-input">
                    <input type="radio" id="isUser_01" name="isAdmin" value="0" checked="checked" />User
                    <input type="radio" id="isAdmin_02" name="isAdmin" value="1" />Admin
                </div>
            </div>

            <div class="submit-box">
                <button id= "submit-button" class="btn btn-primary">submit</button>
            </div>



            <div class="goLogin-box">
                <a href="/login" style="text-decoration: none;">Already have a account？Go login</a>
            </div>


            <%
                String errorInfo = (String) request.getAttribute("registerError");
                if (errorInfo != null) {
            %>
            <script type="text/javascript">
                alert("<%=errorInfo%>");
            </script>
            <%
                }
            %>


        </form>

    </div>

</div>

</body>

</html>
