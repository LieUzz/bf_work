package com.example.quizweb.controller;

import com.example.quizweb.domain.*;
import com.example.quizweb.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    private final LoginService loginService;

    private final UserService userService;

    private final FeedbackService feedbackService;

    private final QuizService quizService;
    private final QuestionService questionService;

    private final QuizQuestionService quizQuestionService;

    public AdminController(LoginService loginService,
                           UserService userService,
                           FeedbackService feedbackService,
                           QuizService quizService,
                           QuestionService questionService,
                           QuizQuestionService quizQuestionService) {
        this.loginService = loginService;
        this.userService = userService;
        this.feedbackService = feedbackService;
        this.quizService = quizService;
        this.questionService = questionService;
        this.quizQuestionService = quizQuestionService;
    }


    @GetMapping("/admin")
    public String getAdmin(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        return "admin";
    }
    @PostMapping("/admin")
    public String postAdmin(HttpServletRequest request, Model model) {
        return "admin";
    }

    @GetMapping("/admin/userProfile")
    public String getUserProfile(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        List<User> users =userService.getAllUserHibernate();

        model.addAttribute("users", users);
        return "admin_user_profile";
    }

    @PostMapping("/admin/userProfile")
    public String postUserProfile(HttpServletRequest request, Model model) {

        List<User> users = userService.getAllUserHibernate();

        return "admin_user_profile";
    }

    @GetMapping("/admin/feedback")
    public String getFeedback(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();

        model.addAttribute("feedbacks", feedbacks);
        return "admin_feedback";
    }

    @PostMapping("/admin/feedback")
    public String postFeedback(HttpServletRequest request, Model model) {


        return "admin_feedback";
    }

    @GetMapping("/admin/quiz")
    public String getQuiz(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        List<QuizResult> quizList = quizService.getAllQuizResult();

        model.addAttribute("quizList", quizList);
        model.addAttribute("users", quizService.filterByUser(quizList));
        model.addAttribute("categories", quizService.filterByCategory(quizList));

        return "admin_quiz";
    }

    @PostMapping("/admin/quiz")
    public String postQuiz(@RequestParam(required = false) String submit,
                           @RequestParam(required = false) Integer User,
                           @RequestParam(required = false) Integer Category,
                           @RequestParam(required = false) Integer quizId,
                           HttpServletRequest request, Model model) {

        if (quizId != null && quizId!=-1) {
            return "redirect:/admin/quiz/result/" + quizId;
        }

        List<QuizResult> quizList = null;
        if (submit!=null) {
            if (submit.equals("Show all")) {
                quizList = quizService.getAllQuizResult();
                System.out.println(submit);
            } else if (submit.equals("Show")) {
                System.out.println("User: " + User + " Category: " + Category);
                if (User != -1) {
                    quizList = quizService.getAllQuizResultByUser(new User(User, "", ""));
                } else if (Category != -1) {
                    quizList = quizService.getAllQuizResultByCategory(new Category(Category, ""));
                } else {
                    quizList = quizService.getAllQuizResult();
                }
            }
        }

        if (quizList != null) {
            for (QuizResult q:quizList)
                System.out.println(q.toString());
            model.addAttribute("quizList", quizList);
            model.addAttribute("users", quizService.filterByUser(quizList));
            model.addAttribute("categories", quizService.filterByCategory(quizList));
        }
        return "admin_quiz";
    }


    @GetMapping("/admin/quiz/result/{quizId}")
    public String getQuizResult(@PathVariable(required = false) Integer quizId,
                                HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        System.out.println("^^^^^^^^^" + quizId);
        if (quizId != null) {
            QuizResult quizResult = quizService.filterByQuizId(quizId);
            System.out.println("%%%%%" + quizResult.toString());
            model.addAttribute("quiz", quizResult);
            return "admin_quiz_result";
        }
        return "redirect:/admin/quiz";
    }

    @PostMapping("/admin/quiz/result")
    public String postQuizResult(HttpServletRequest request, Model model) {
        return "admin_quiz_result";
    }

    @GetMapping("/admin/question")
    public String getQuestionEdit(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        List<Question> questions = questionService.getAllQuestionHibernate();
        model.addAttribute("questions", questions);
        return "admin_quiz_question";
    }

    @PostMapping("/admin/question")
    public String postQuestionEdit(HttpServletRequest request, Model model) {

        List<Question> questions = questionService.getAllQuestionHibernate();
        model.addAttribute("questions", questions);
        return "admin_quiz_question";
    }


}
