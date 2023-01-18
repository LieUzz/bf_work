<%--
  Created by IntelliJ IDEA.
  User: zhengjiayu
  Date: 2022/12/31
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Online Quiz !</title>
</head>
<body>
<p>Hi, ${user.firstname} ${user.lastname}</p>
<p>This is ${quiz.quiz_name} quiz</p>
<p>You took this quiz at ${quiz.start_time}, and finished this exam at ${quiz.end_time}"</p>
<p>Your final score is ${score}/5</p>
<p>You ${passornot} this quiz.</p>

<h3> The correct answer is as follows </h3>

<div class="quiz-input">
    <c:forEach items="${quizQuestionList}" var="question" varStatus="loop">
        <div>
            <p> Question ${question.question.description} Options are: </p>
            <c:forEach items="${question.question.choices}" var="choice" varStatus="loop">
                <p>${choice.description}</p>
            </c:forEach>
            <p> your answer is ${question.choice.description}, and you are ${question.is_correct}</p>
        </div>
    </c:forEach>
</div>
<div id="submit-box">
    <a href="/home"><input type="submit" class="btn btn-info" value="anthor quiz"></a>
</div>
</body>
</html>
