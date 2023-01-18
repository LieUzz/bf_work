package com.example.onlinequiz.web;

import com.example.onlinequiz.dao.*;
import com.example.onlinequiz.domain.Feedback;
import com.example.onlinequiz.domain.Question;
import com.example.onlinequiz.domain.Quiz;
import com.example.onlinequiz.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    UserDao userDao;

    @Autowired
    QuestionDao questionDao;

    @Autowired
    QuizDao quizDao;

    @Autowired
    FeedbackDao feedbackDao;

    @Autowired
    QuizRecordDao quizRecordDao;

    private User user;

    private List<Question> questionList;
    private Quiz quiz;

    private String[] userResult;

    // questionNumber
    private Integer qNumber;

    private String startTime;

    private String endTime;




//    @RequestMapping("/login")
//    public String login(){
//        User user = userDao.getUserById(1);
//        System.out.println(user.toString());
//        return "login";
//    }

    @RequestMapping("home")
    public String doLogin(User user, Map<String,Object> map, HttpServletRequest request){

        if (this.user != null) {
            System.out.println(this.user.toString());
            return "home";
        }

        User user1 = userDao.getUser(user.getName(),user.getPassword());
//        System.out.println(user.toString());
        if (user1==null){
            return "redirect:login?error";
        }else{
//            request.getSession().getAttribute("session-user");
            this.user = user1;
            return "home";
        }
    }

    @RequestMapping("fail")
    public String doFail(Map<String,Object> map, HttpServletRequest request){

        System.out.println("%%%%%" + map.get("quizType"));
        return "home";
    }

    @RequestMapping("/register")
    public String regist(){
        return "register";
    }

    @RequestMapping("login")
    public String doRegister(User user, Map<String,Object> map, HttpServletRequest request){


        User user1 = userDao.getUserByName(user.getName());
//        System.out.println("*******" + user.toString());
        if (user1!=null){
            return "redirect:register?error";
        }else {
            userDao.createNewUser(user.getName(), user.getPassword(),user.getPhone(),user.getEmail());
            return "login";
        }
    }

//    @RequestMapping("startQuiz")
//    public String doStart(Map<String,Object> map, HttpServletRequest request){
//        if (this.startTime.equals(null)) {
//            System.out.println("%%% I am here");
//            SimpleDateFormat sdf = new SimpleDateFormat();
//            sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
//            Date date = new Date();
//            this.startTime = sdf.format(date);
////            Quiz quiz1 = quizDao.getQuizByQuizType(quiz.getQuizType());
//            Quiz quiz = quizDao.getQuizByQuizType((String)map.get("quizType"));
//            List<Question> questions = questionDao.getUserById(quiz.getId());
//            this.userResult = new String[10];
//            this.qNumber = 0;
//            this.quiz = quiz;
//            this.questionList = questions;
//        }
//        System.out.println(map.get("star"));
//        Question question = this.questionList.get(this.qNumber);
//        map.put("question", question.getQuestion());
//        return "startQuiz";
//    }



    @RequestMapping("quizScreen")
    public String takeQuiz(HttpServletRequest request, Map<String,Object> map){
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        this.startTime = sdf.format(date);

        Quiz quiz1 = quizDao.getQuizByQuizType(request.getParameter("quizType"));
        List<Question> questions = questionDao.getUserById(quiz1.getId());
        this.userResult = new String[10];
        this.qNumber = 0;
        this.quiz = quiz1;
        this.questionList = questions;
        Question question = this.questionList.get(this.qNumber);
        map.put("quiztype", this.quiz.getQuizType());
        map.put("qNumber", this.qNumber+1);
        map.put("question", question.getQuestion());
        map.put("opt1", question.getOpt1());
        map.put("opt2", question.getOpt2());
        map.put("opt3", question.getOpt3());
        map.put("opt4", question.getOpt4());
        System.out.println("&&&&&" + map.get("opt1"));

        System.out.println(this.quiz.toString());
        System.out.println(this.questionList.get(0).toString());
        return "quizScreen";
    }

    @RequestMapping("/next")
    public String nextQuestion(HttpServletRequest request, ModelMap m){

        System.out.println("******" + request.getParameter("opt1"));

        System.out.println(this.user.toString());

        this.qNumber++;
        Question question = this.questionList.get(this.qNumber);

        m.addAttribute("quiztype", this.quiz.getQuizType());
        m.addAttribute("qNumber", this.qNumber+1);
        m.addAttribute("question", question.getQuestion());

        m.addAttribute("opt1", question.getOpt1());
        m.addAttribute("opt2", question.getOpt2());
        m.addAttribute("opt3", question.getOpt3());
        m.addAttribute("opt4", question.getOpt4());


        System.out.println(this.qNumber);
        System.out.println(question.toString());
        return "quizScreen";
    }

    @RequestMapping("/prev")
    public String prevQuestion(HttpServletRequest request, ModelMap m){
        System.out.println("******");
//        System.out.println("******" + request.getParameter("prevOrNext"));

        System.out.println(this.user.toString());

        this.qNumber--;
        Question question = this.questionList.get(this.qNumber);

        m.addAttribute("quiztype", this.quiz.getQuizType());
        m.addAttribute("qNumber", this.qNumber+1);
        m.addAttribute("question", question.getQuestion());

        m.addAttribute("opt1", question.getOpt1());
        m.addAttribute("opt2", question.getOpt2());
        m.addAttribute("opt3", question.getOpt3());
        m.addAttribute("opt4", question.getOpt4());


        System.out.println(this.quiz.toString());
        System.out.println(this.questionList.get(0).toString());
        return "quizScreen";
    }


    @RequestMapping("/quizResult")
    public String quizResult(HttpServletRequest request, ModelMap m){

        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String endTime = sdf.format(date);


        // because there is a problem, that I can not get clicked answer result back from html
        // and store user answer result into userResult, so I manually write some data into userResult.
        userResult[0] = this.questionList.get(0).getCorrect();
        userResult[1] = this.questionList.get(1).getCorrect();
        userResult[2] = this.questionList.get(2).getCorrect();
        userResult[3] = this.questionList.get(3).getCorrect();
        userResult[4] = this.questionList.get(4).getCorrect();



        int res = 0;

        for (int i = 0; i < this.questionList.size(); i++) {
            Question qes = this.questionList.get(i);
            if (qes.getCorrect().equals(userResult[i])) {
                res ++;
            }
        }

        m.addAttribute("username", this.user.getName());
        m.addAttribute("quizname", this.quiz.getQuizType());
        m.addAttribute("score", res);
        m.addAttribute("starttime", this.startTime);
        m.addAttribute("endtime", endTime);

        if (res >= 6)
            m.addAttribute("passornot", "PASS");
        else
            m.addAttribute("passornot", "FAILED");

        for (int i = 0; i < this.questionList.size(); i++) {
            int idx = i+1;
            String qidx = "qidx" + idx;
            String q = "q" + idx;
            String a = "a" + idx;
            System.out.println(q + a);
            Question qes = this.questionList.get(i);
            m.addAttribute(qidx, idx);
            m.addAttribute(q, qes.getCorrect());
            m.addAttribute(a, userResult[i]);
        }

        quizRecordDao.createNewUser(this.user.getId(), this.quiz.getId(), res, this.startTime, endTime);

        this.startTime = "";
        return "quizResult";
    }


    @RequestMapping("feedback")
    public String getFeedback(User user, Map<String,Object> map, HttpServletRequest request){
        return "feedback";
    }

    @RequestMapping("submit-feedback")
    public String submitFeedback(Map<String,Object> map, HttpServletRequest request){
        System.out.println(request.getParameter("feedback"));


        feedbackDao.createNewFeedBack(Integer. valueOf(request.getParameter("star")), request.getParameter("feedback"));

        return "submit-feedback";
    }

//    @RequestMapping("startQuiz")
//    public String tmp(Map<String,Object> map, HttpServletRequest request){
//        System.out.println("^^^^^^^^" + request.getParameter("quizType"));
//        map.put("quiztype", request.getParameter("quizType"));
//
//        Quiz quiz1 = quizDao.getQuizByQuizType(request.getParameter("quizType"));
//        List<Question> questions = questionDao.getUserById(quiz1.getId());
//        map.put("question", questions.get(0).getQuestion());
//
//        System.out.println("&&&&&&" + request.getParameter("star"));
//        return "startQuiz";
//    }









}
