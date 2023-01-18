<%--
  Created by IntelliJ IDEA.
  User: zhengjiayu
  Date: 2022/12/31
  Time: 13:20
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
    input{
        display: inline-block;
        background: #fff;
    }
    .container{
        width: 100%;
    }
    .quiz-screen-box{
        position: relative;
        height: 1200px;
        width: 2000px;
        top: 50px;
        margin: 0 auto;
        z-index: 99999;
        background:#ffffff;
        border: 7px solid #ccc;
    }
    .title-box{
        position: absolute;
        width: 500px;
        height: 50px;
        margin-left: 800px;
        margin-top: 5px;
        text-align: center;
        font-size: 40px;
        font-weight: 800;
        color: #ff5000;
        line-height: 50px;
    }
    .question-box{
        position: absolute;
        width: 800px;
        height: 40px;
        line-height: 40px;
        margin-top:200px;
        margin-left:300px;
        font-weight: 700;
        font-size: 30px;
    }
    .opt-box {
        position: absolute;
        margin-top:400px;
        margin-left:400px;
        font-weight: 700;
        font-size: 30px;

    }
    .options{
        /*display: inline-block;*/
        margin-left: 20px;
    }
    .change-question-box{
        position: absolute;
        width: 500px;
        height: 500px;
        font-size: 20px;
        margin-left: 300px;
        margin-top: 500px;
    }
    .button-prev{
        position: absolute;
        width: 100px;
        height: 40px;
        margin-left: 300px;
        margin-top: 1000px;
    }

    .button-next{
        position: absolute;
        margin-left: 1700px;
        margin-top: 1000px;
    }


</style>

<body>
<div class="container">
    <div class="quiz-screen-box">
        <form action="${pageContext.request.contextPath}/quiz" method="post">
            <div class="title-box">
                <span>
                    <p >${quiz.quiz_name} Quiz Time</p>
                </span>
            </div>

            <div class="navigator">

                <div style="display: inline-block">
                    <input type="submit" class="btn btn-info" name="submit" value="1">
                </div>
                <div style="display: inline-block">
                    <input type="submit" class="btn btn-info" name="submit" value="2">
                </div>
                <div style="display: inline-block">
                    <input type="submit" class="btn btn-info" name="submit" value="3">
                </div>
                <div style="display: inline-block">
                    <input type="submit" class="btn btn-info" name="submit" value="4">
                </div>
                <div style="display: inline-block">
                    <input type="submit" class="btn btn-info" name="submit" value="5">
                </div>

            </div>

            <div class="question-box">
                <span>
                    <p >Question ${question_number}: </p>
                </span>
                <span>
                    <p >${question.description}</p>
                </span>
            </div>

            <div class="opt-box">
                <c:forEach items="${question.choices}" var="choice" varStatus="loop">
                    <div class="options">
                        <input type="radio"
                               name="choiceId"
                               value="${choice.id}"/>
                            ${choice.description}
                    </div>
                </c:forEach>
            </div>


            <div id="submit-box">
                <input type="submit" class="btn btn-info" name="submit" value="Submit">
            </div>

            <%
                String errorInfo = (String) request.getAttribute("not_finish");
                if (errorInfo != null) {
            %>
            <script type="text/javascript">
                var elems = document.getElementsByClassName('confirmation');
                var confirmIt = function (e) {
                    if (!confirm('Are you sure to submit?')) e.preventDefault();
                };
                for (var i = 0, l = elems.length; i < l; i++) {
                    elems[i].addEventListener('click', confirmIt, false);
                }
            </script>
            <%
                }
            %>

            <%
                Integer qNumber = (Integer) request.getAttribute("question_number");
                if (qNumber != 1) {
            %>
            <div class="button-prev">
<%--                <a href="/quiz/${question_number-2}"><input type="submit" class="btn btn-info" name="prevOrNext" value="Prev"> </a>--%>
                <input type="submit" class="btn btn-info" name="submit" value="Prev">
            </div>
            <%
                }
                if (qNumber != 5) {
            %>
            <div class="button-next">
<%--                <a href="/quiz/${question_number}"><input type="submit" class="btn btn-info" name="prevOrNext" value="Next"> </a>--%>
                <input type="submit" class="btn btn-info" name="submit" value="Next">
            </div>
            <%
                }
            %>
        </form>

    </div>
</div>
</body>

</html>
